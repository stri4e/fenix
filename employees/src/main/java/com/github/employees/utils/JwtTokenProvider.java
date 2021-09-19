package com.github.employees.utils;

import com.github.employees.entities.Employee;
import com.github.employees.entities.RefreshSession;
import com.github.employees.entities.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
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

    @Value("${app.admin.expire.time}")
    private int adminExpireTime;

    @Value("${app.manager.expire.time}")
    private int managerExpireTime;

    @Value("${app.refresh.expire.time}")
    private int refreshExpireTime;

    public String accessToken(Employee employee, List<Role> roles) {
        List<String> rolesNames = roles.stream()
                .map(Role::getRole).collect(Collectors.toList());
        if (rolesNames.contains("ROLE_ADMIN")) {
            var keyId = this.keysRole.get("admin");
            return accessToken(employee, roles, this.adminExpireTime, keyId, this.keysStore.get(keyId));
        } else if (rolesNames.contains("ROLE_MANAGER")) {
            var keyId = this.keysRole.get("manager");
            return accessToken(employee, roles, this.managerExpireTime, keyId, this.keysStore.get(keyId));
        } else {
            throw new RuntimeException("Not find actual role!");
        }
    }

    public RefreshSession
    refreshSession(String fingerprint, String ip, Employee employee, List<Role> roles) {
        List<String> rolesNames = roles.stream()
                .map(Role::getRole).collect(Collectors.toList());
        var now = new Date();
        var expire = new Date(now.getTime() + this.refreshExpireTime);
        if (rolesNames.contains("ROLE_ADMIN")) {
            var keyId = this.keysRole.get("admin");
            var token = refreshToken(fingerprint, expire, employee, keyId, this.keysStore.get(keyId));
            return new RefreshSession(employee.getId(), token, fingerprint, ip, expire);
        } else if (rolesNames.contains("ROLE_MANAGER")) {
            var keyId = this.keysRole.get("manager");
            var token = refreshToken(fingerprint, expire, employee, keyId, this.keysStore.get(keyId));
            return new RefreshSession(employee.getId(), token, fingerprint, ip, expire);
        } else {
            throw new RuntimeException("Not find actual role!");
        }
    }

    private String accessToken(Employee employee, List<Role> roles, int expireTime, String keyId, String key) {
        if (Objects.nonNull(employee)) {
            var now = new Date();
            var date = new Date(now.getTime() + expireTime);
            List<String> authorities = roles.stream()
                    .map(Role::getRole)
                    .collect(Collectors.toList());
            return Jwts.builder()
                    .setSubject(employee.getId().toString())
                    .claim(AUTHORITIES, authorities)
                    .claim("firstName", employee.getFirstName())
                    .claim("lastName", employee.getLastName())
                    .setHeaderParam(JwsHeader.KEY_ID, keyId)
                    .setIssuedAt(new Date())
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
        }
        return null;
    }

    private String refreshToken(String fingerprint, Date expire, Employee user, String keyId, String key) {
        if (Objects.nonNull(user)) {
            return Jwts.builder()
                    .setSubject(user.getId().toString())
                    .claim("fingerprint", fingerprint)
                    .claim("firstName", user.getFirstName())
                    .claim("lastName", user.getLastName())
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
