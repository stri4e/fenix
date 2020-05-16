package com.github.gateway.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String AUTHORITIES = "AUTHORITIES";

    @Value("${app.user.secret.key}")
    private String userSecretKey;

    public Long getUserFromJwt(final String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.userSecretKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public List<String> getRoles(final String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.userSecretKey)
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
            Jwts.parser().setSigningKey(this.userSecretKey).parseClaimsJws(authToken);
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
