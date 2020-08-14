package com.github.users.center.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.github.users.center.payload.TokenType.TYPE_HTTP_TOKEN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshResponse {

    private String tokenType;

    private String accessToken;

    private String refreshToken;

    private long expireIn;

    public JwtRefreshResponse(String accessToken, String refreshToken, long expireIn) {
        this.tokenType = TYPE_HTTP_TOKEN;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireIn = expireIn;
    }
}
