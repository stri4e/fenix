package com.github.employees.services;

import com.github.employees.entities.Employee;
import com.github.employees.payload.AccessTokenResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public interface IRefreshSessionService {

    Mono<ResponseEntity<AccessTokenResponse>> newSession(Employee employee, String ip, String fingerprint, String userAgent);

    Mono<ResponseEntity<AccessTokenResponse>> updateSession(String ip, String userAgent, String refreshToken);

    Mono<ResponseEntity<Void>> removeSession(String refreshToken);

}
