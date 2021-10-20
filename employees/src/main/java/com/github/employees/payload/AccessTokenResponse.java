package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenResponse {

    public static final String TYPE_HTTP_TOKEN = "Bearer";

    private String tokenType;

    private String accessToken;

    public AccessTokenResponse(String accessToken) {
        this.tokenType = TYPE_HTTP_TOKEN;
        this.accessToken = accessToken;
    }
}
