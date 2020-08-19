package com.github.users.center.utils;

import com.github.users.center.entity.RefreshSession;
import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES = "AUTHORITIES";

    @Value("${app.user.secret.key}")
    private String userSecretKey;

    @Value("${app.user.expire.time}")
    private int userExpireTime;

    @Value("${app.admin.expire.time}")
    private int adminExpireTime;

    @Value("${app.manager.expire.time}")
    private int managerExpireTime;

    @Value("${app.refresh.expire.time}")
    private int refreshExpireTime;

    @Value("${app.refresh.key}")
    private String refreshKey;

    public String userAccessToken(User user) {
        return accessToken(user, this.userExpireTime);
    }

    public String adminAccessToken(User user) {
        return accessToken(user, this.adminExpireTime);
    }

    public String managerAccessToken(User user) {
        return accessToken(user, this.managerExpireTime);
    }

    private String accessToken(User user, int expireTime) {
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
                    .setIssuedAt(new Date())
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS512, this.userSecretKey)
                    .compact();
        }
        return null;
    }

    public RefreshSession
    refreshSession(String fingerprint, String location, User user, String scope) {
        var now = new Date();
        var expire = new Date(now.getTime() + this.refreshExpireTime);
        var token = refreshToken(fingerprint, expire, user, scope);
        return new RefreshSession(user.getId(), token, fingerprint, location, expire, now);
    }

    private String refreshToken(String fingerprint, Date expire, User user, String scope) {
        if (Objects.nonNull(user)) {
            return Jwts.builder()
                    .setSubject(user.getId().toString())
                    .claim("fingerprint", fingerprint)
                    .claim("firstName", user.getFName())
                    .claim("lastName", user.getLName())
                    .claim("scope", scope)
                    .setIssuedAt(new Date())
                    .setExpiration(expire)
                    .signWith(SignatureAlgorithm.HS512, this.refreshKey)
                    .compact();
        }
        return null;
    }

    public Long fetchUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.refreshKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public String fetchFingerprint(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.refreshKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("fingerprint", String.class);
    }

    public boolean validateRefreshToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.refreshKey).parseClaimsJws(authToken);
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

}
