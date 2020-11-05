package com.github.payments.utils;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class JwtTokenProvider {

    public UUID getUserFromJwt(String token) {
        return UUID.fromString(subject(token));
    }

    private String subject(String token) {
        var signatureIndex = token.lastIndexOf('.');
        var nonSignedToken = token.substring(0, signatureIndex + 1);
        return  Jwts.parser().parseClaimsJwt(nonSignedToken).getBody().getSubject();
    }

}
