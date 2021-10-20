package com.github.employees.controllers;

import com.github.employees.payload.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IEmployeesSecurityController {

    @PostMapping(path = "/edit/registration")
    Mono<RegistrationResponse> submitRegistration(EmployeeDetailDto payload);

    @PostMapping(path = "/auth")
    Mono<ResponseEntity<AccessTokenResponse>>
    submitAuth(String ip, String fingerprint, String userAgent, EmployAuthDto payload);

    @PostMapping(path = "/logout")
    Mono<ResponseEntity<Void>>
    submitLogout(@CookieValue(name = "refresh_token") String refreshToken);

    @PutMapping(path = "/edit")
    Mono<Void> locked(@RequestBody EmployeeAccessDto payload);

}
