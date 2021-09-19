package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshResponse {

    public static final String TYPE_HTTP_TOKEN = "Bearer";

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
