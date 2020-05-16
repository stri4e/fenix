package com.github.users.center.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshResponse {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private long expireAt;
}
