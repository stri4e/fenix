package com.github.gateway.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String AUTHORITIES = "AUTHORITIES";

    @Value(value = "#{${keys.store}}")
    private Map<String, String> keysStore;

    public String findKey(String authToken) {
        return this.keysStore.get(getKeyId(authToken));
    }

    public Long getUserFromJwt(String token, String key) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public List<String> getRoles(String token, String key) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.get(AUTHORITIES, getObjectType());
    }

    @SuppressWarnings("unchecked")
    public Class<List<String>> getObjectType() {
        return (Class<List<String>>) ((Class)List.class);
    }

    public boolean validateToken(final String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(this.keysStore.get(getKeyId(authToken)))
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
