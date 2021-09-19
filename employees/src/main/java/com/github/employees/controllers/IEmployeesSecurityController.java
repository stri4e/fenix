package com.github.employees.controllers;

import com.github.employees.payload.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import reactor.core.publisher.Mono;

public interface IEmployeesSecurityController {

    @PostMapping(path = "/edit/registration")
    Mono<RegistrationResponse> registration(EmployeeDetailDto payload);

    @PostMapping(path = "/auth")
    Mono<JwtRefreshResponse> submitAuth(String ip, String fingerprint, String userAgent, EmployAuthDto payload);

    @PutMapping(path = "/edit")
    Mono<Void> locked();

}
