package com.github.employees.utils;

import com.github.employees.entities.Employee;
import com.github.employees.entities.RefreshSession;
import com.github.employees.entities.Role;
import com.github.employees.models.AccessKey;
import com.github.employees.models.KeysStore;
import com.github.employees.models.RefreshKey;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String AUTHORITIES = "AUTHORITIES";

    private final Map<String, KeysStore> keysStore;

    private final Map<String, AccessKey> accessKeyStore;

    private final Map<String, RefreshKey> refreshKeyStore;

    public String accessToken(Employee employee, List<Role> roles) {
        KeysStore keysStore = roles.stream().map(role -> this.keysStore.get(role.getRole()))
                .max(Comparator.comparing(KeysStore::getPriority))
                .orElseThrow();
        AccessKey key = keysStore.getAccessKey();
        return accessToken(employee, roles, key.getExpirationTime(), key.getId(), key.getKey());
    }

    public RefreshSession
    refreshSession(String fingerprint, String ip, Employee employee, List<Role> roles) {
        KeysStore keysStore = roles.stream().map(role -> this.keysStore.get(role.getRole()))
                .max(Comparator.comparing(KeysStore::getPriority))
                .orElseThrow();
        RefreshKey key = keysStore.getRefreshKey();
        var now = new Date();
        var expire = new Date(now.getTime() + key.getExpirationTime());
        var token = refreshToken(fingerprint, expire, employee, key.getId(), key.getKey());
        return new RefreshSession(employee.getId(), token, fingerprint, ip, expire);
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
                    .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)), SignatureAlgorithm.HS512)
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
                    .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)), SignatureAlgorithm.HS512)
                    .compact();
        }
        return null;
    }

    public UUID fetchSubjectFromAccessToken(String token) {
        String keyId = getKeyId(token);
        return fetchSubject(token, this.accessKeyStore.get(keyId).getKey());
    }

    public UUID fetchSubjectRefreshToken(String token) {
        String keyId = getKeyId(token);
        return fetchSubject(token, this.refreshKeyStore.get(keyId).getKey());
    }

    public UUID fetchSubject(String token, String key) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return UUID.fromString(claims.getSubject());
    }

    public String fetchRefreshTokenFingerprint(String token) {
        return fetchFingerprint(token, this.refreshKeyStore.get(getKeyId(token)).getKey());
    }

    public String fetchFingerprint(String token, String key) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("fingerprint", String.class);
    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, this.accessKeyStore.get(getKeyId(token)).getKey());
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token, this.refreshKeyStore.get(getKeyId(token)).getKey());
    }

    public boolean validateToken(String token, String key) {
        try {
            Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
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
        return (String) h.get(JwsHeader.KEY_ID);
    }

}
