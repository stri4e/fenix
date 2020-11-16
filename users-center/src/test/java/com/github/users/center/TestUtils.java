package com.github.users.center;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.UUID;

public class TestUtils {

    public static UUID parserUserToken(String token) {
        Claims claims = Jwts.parser().setSigningKey("WgmSvorXxWW0hM2RdXVwAlmo7JAzFFtdFmSvjXdXFlZb6x266NZvzj5ua6NsYQUpS1/qiJCRSUw7uihOcNNlig==")
                .parseClaimsJws(token).getBody();
        return UUID.fromString(claims.getSubject());
    }

    public static UUID parserAdminToken(String token) {
        Claims claims = Jwts.parser().setSigningKey("PJA/5w8+M+/M1HCUFVN7H5JXfqT2qtyyK9ZpKdTzfoqklVhigahDQmNWEDY04hF5mFQVtjEC8BzCjM9iWpf9GQ==")
                .parseClaimsJws(token).getBody();
        return UUID.fromString(claims.getSubject());
    }

}
