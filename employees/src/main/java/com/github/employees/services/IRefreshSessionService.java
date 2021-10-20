package com.github.employees.services;

import com.github.employees.entities.Employee;
import com.github.employees.payload.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface IRefreshSessionService {

    Mono<ResponseEntity<AccessTokenResponse>> session(Employee employee, String ip, String fingerprint, String userAgent);

}
