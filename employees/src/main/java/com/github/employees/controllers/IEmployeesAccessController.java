package com.github.employees.controllers;

import com.github.employees.payload.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public interface IEmployeesAccessController {

    @PostMapping(path = "/edit/registration")
    Mono<RegistrationResponse> submitRegistration(EmployeeDetailDto payload);

    @PostMapping(path = "/auth")
    Mono<ResponseEntity<AccessTokenResponse>>
    submitAuth(@RequestAttribute(name = "ip") String ip,
               @RequestHeader(name = "ETag") String fingerprint,
               @RequestHeader(name = "User-Agent") String userAgent,
               EmployAuthDto payload
    );

    @GetMapping(path = "/logout")
    Mono<ResponseEntity<Void>>
    submitLogout(@CookieValue(name = "refresh_token", required = false) String refreshToken);

    @PutMapping(path = "/edit")
    Mono<Void> locked(@RequestBody EmployeeAccessDto payload);

}
