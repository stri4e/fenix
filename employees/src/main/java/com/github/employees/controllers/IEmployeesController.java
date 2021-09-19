package com.github.employees.controllers;

import com.github.employees.payload.*;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

public interface IEmployeesController {

    @PostMapping(path = "/edit/registration")
    Mono<RegistrationResponse> registration(EmployeeDto payload);

    @PostMapping(path = "/auth")
    Mono<JwtRefreshResponse> submitAuth(String ip, String fingerprint, String userAgent, EmployAuthDto payload);

}
