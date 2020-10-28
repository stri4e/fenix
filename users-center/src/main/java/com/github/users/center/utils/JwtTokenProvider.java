package com.github.users.center.utils;

import com.github.users.center.entity.RefreshSession;
import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES = "AUTHORITIES";

    @Value(value = "#{${keys.role}}")
    private Map<String, String> keysRole;

    @Value(value = "#{${keys.store}}")
    private Map<String, String> keysStore;

    @Value("${app.user.expire.time}")
    private int userExpireTime;

    @Value("${app.admin.expire.time}")
    private int adminExpireTime;

    @Value("${app.manager.expire.time}")
    private int managerExpireTime;

    @Value("${app.refresh.expire.time}")
    private int refreshExpireTime;

    public String userAccessToken(User user) {
        var keyId = this.keysRole.get("user");
        return accessToken(user, this.userExpireTime, keyId, this.keysStore.get(keyId));
    }

    public String adminAccessToken(User user) {
        var keyId = this.keysRole.get("admin");
        return accessToken(user, this.adminExpireTime, keyId, this.keysStore.get(keyId));
    }

    public String managerAccessToken(User user) {
        var keyId = this.keysRole.get("manager");
        return accessToken(user, this.managerExpireTime, keyId, this.keysStore.get(keyId));
    }

    private String accessToken(User user, int expireTime, String keyId, String key) {
        if (Objects.nonNull(user)) {
            var now = new Date();
            var date = new Date(now.getTime() + expireTime);
            List<String> authorities = user.getRoles().stream()
                    .map(Role::getRole)
                    .collect(Collectors.toList());
            return Jwts.builder()
                    .setSubject(user.getId().toString())
                    .claim(AUTHORITIES, authorities)
                    .claim("firstName", user.getFName())
                    .claim("lastName", user.getLName())
                    .setHeaderParam(JwsHeader.KEY_ID, keyId)
                    .setIssuedAt(new Date())
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
        }
        return null;
    }

    public RefreshSession
    refreshAdminSession(String fingerprint, String location, User user, String scope) {
        var now = new Date();
        var expire = new Date(now.getTime() + this.refreshExpireTime);
        var keyId = this.keysRole.get("refresh_admin");
        var token = refreshToken(fingerprint, expire, user, scope, keyId, this.keysStore.get(keyId));
        return new RefreshSession(user.getId(), token, fingerprint, location, expire);
    }

    public RefreshSession
    refreshManagerSession(String fingerprint, String location, User user, String scope) {
        var now = new Date();
        var expire = new Date(now.getTime() + this.refreshExpireTime);
        var keyId = this.keysRole.get("refresh_manager");
        var token = refreshToken(fingerprint, expire, user, scope, keyId, this.keysStore.get(keyId));
        return new RefreshSession(user.getId(), token, fingerprint, location, expire);
    }

    private String refreshToken(String fingerprint, Date expire, User user, String scope, String keyId, String key) {
        if (Objects.nonNull(user)) {
            return Jwts.builder()
                    .setSubject(user.getId().toString())
                    .claim("fingerprint", fingerprint)
                    .claim("firstName", user.getFName())
                    .claim("lastName", user.getLName())
                    .claim("scope", scope)
                    .setHeaderParam(JwsHeader.KEY_ID, keyId)
                    .setIssuedAt(new Date())
                    .setExpiration(expire)
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
        }
        return null;
    }

    public UUID fetchUser(String token) {
        var keyId = getKeyId(token);
        Claims claims = Jwts.parser()
                .setSigningKey(this.keysStore.get(keyId))
                .parseClaimsJws(token)
                .getBody();
        return UUID.fromString(claims.getSubject());
    }

    public String fetchFingerprint(String token) {
        var keyId = getKeyId(token);
        Claims claims = Jwts.parser()
                .setSigningKey(this.keysStore.get(keyId))
                .parseClaimsJws(token)
                .getBody();
        return claims.get("fingerprint", String.class);
    }

    public boolean validateRefreshToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.keysStore.get(getKeyId(authToken)))
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    private String getKeyId(String token) {
        var signatureIndex = token.lastIndexOf('.');
        var nonSignedToken = token.substring(0, signatureIndex + 1);
        Header<?> h = Jwts.parser().parseClaimsJwt(nonSignedToken).getHeader();
        return  (String) h.get(JwsHeader.KEY_ID);
    }

}
