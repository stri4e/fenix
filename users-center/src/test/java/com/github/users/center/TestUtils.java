package com.github.users.center;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TestUtils {

    public static Long parserToken(String token) {
        Claims claims = Jwts.parser().setSigningKey("205d36838e32d2905b2b65988a201aec50c4a29795c71acdb32f60768a1dd123")
                .parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
