package com.github.orders.utils;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value(value = "#{${keys.store}}")
    private Map<String, String> keysStore;

    public Long getUserFromJwt(String token) {
        var claims = Jwts.parser()
                .setSigningKey(this.keysStore.get(getKeyId(token)))
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    private String getKeyId(String token) {
        var signatureIndex = token.lastIndexOf('.');
        var nonSignedToken = token.substring(0, signatureIndex + 1);
        Header<?> h = Jwts.parser().parseClaimsJwt(nonSignedToken).getHeader();
        return  (String) h.get(JwsHeader.KEY_ID);
    }

}
