package com.github.employees.services;

import com.github.employees.entities.Employee;
import com.github.employees.payload.JwtRefreshResponse;
import reactor.core.publisher.Mono;

public interface IRefreshSessionService {

    Mono<JwtRefreshResponse> session(Employee employee, String ip, String fingerprint, String userAgent);

}
