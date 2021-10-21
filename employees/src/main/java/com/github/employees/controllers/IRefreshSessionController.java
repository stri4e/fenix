package com.github.employees.controllers;

import com.github.employees.payload.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

public interface IRefreshSessionController {

    @PostMapping
    Mono<ResponseEntity<AccessTokenResponse>>
    submitRefreshSession(
            @RequestAttribute(name = "ip") String ip,
            @RequestHeader(name = "User-Agent") String userAgent,
            @CookieValue(name = "refresh_token") String token
    );

}
