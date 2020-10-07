package com.github.ethereum.utils;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${app.user.secret.key}")
    private String userSecretKey;

    public Long getUserFromJwt(final String token) {
        var claims = Jwts.parser()
                .setSigningKey(this.userSecretKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }


}
