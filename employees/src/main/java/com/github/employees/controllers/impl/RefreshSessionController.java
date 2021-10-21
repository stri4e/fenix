package com.github.employees.controllers.impl;

import com.github.employees.controllers.IRefreshSessionController;
import com.github.employees.payload.AccessTokenResponse;
import com.github.employees.services.IRefreshSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/refresh/sessions")
public class RefreshSessionController implements IRefreshSessionController {

    private final IRefreshSessionService refreshSessionService;

    @Override
    public Mono<ResponseEntity<AccessTokenResponse>>
    submitRefreshSession(String ip, String userAgent, String token) {
        return this.refreshSessionService.updateSession(ip, userAgent, token);
    }

}
