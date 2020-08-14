package com.github.users.center.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.github.users.center.payload.TokenType.TYPE_HTTP_TOKEN;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse {

    private String tokenType;
    private String accessToken;

    public JwtAuthResponse(String tokenType) {
        this.tokenType = TYPE_HTTP_TOKEN;
        this.tokenType = tokenType;
    }

}
