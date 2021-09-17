package com.github.employees.controllers;

import com.github.employees.payload.EmployAuthDto;
import com.github.employees.payload.EmployeeDto;
import com.github.employees.payload.JwtAuthResponse;
import com.github.employees.payload.RegistrationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

public interface IEmployeesController {

    @PostMapping(path = "/edit/registration")
    Mono<RegistrationResponse> registration(EmployeeDto payload);

    @PostMapping(path = "/auth")
    JwtAuthResponse submitAuth(String ip, String userAgent, EmployAuthDto payload);

}
