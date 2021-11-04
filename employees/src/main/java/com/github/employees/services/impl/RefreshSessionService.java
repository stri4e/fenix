package com.github.employees.services.impl;

import com.github.employees.entities.Employee;
import com.github.employees.entities.RefreshSession;
import com.github.employees.entities.Role;
import com.github.employees.payload.AccessTokenResponse;
import com.github.employees.repository.EmployeesRepo;
import com.github.employees.repository.RefreshSessionRepo;
import com.github.employees.services.IRefreshSessionService;
import com.github.jwt.tokens.models.KeysStore;
import com.github.jwt.tokens.models.RefreshKey;
import com.github.jwt.tokens.utils.JwtTokenGenerator;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RefreshSessionService implements IRefreshSessionService {

    private final EmployeesRepo employeesRepo;

    private final Map<String, KeysStore> keysStore;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final RefreshSessionRepo refreshSessionRepo;

    @Override
    public Mono<ResponseEntity<AccessTokenResponse>>
    newSession(Employee employee, String ip, String fingerprint, String userAgent) {
        var now = new Date();
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        return Mono.zip(this.refreshSessionRepo.findByEmployeeId(employee.getId())
                        .map(oldSession -> this.refreshSessionRepo.save(oldSession.statusOff())),
                collectResponseEntity(employee, ip, fingerprint, now, employee.getRoles(), agent))
                .map(Tuple2::getT2);
    }

    @Override
    public Mono<ResponseEntity<AccessTokenResponse>> updateSession(String ip, String userAgent, String refreshToken) {
        var now = new Date();
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        var sessionId = this.jwtTokenGenerator.fetchRefreshTokenSessionId(refreshToken);
        var fingerprint = this.jwtTokenGenerator.fetchRefreshTokenFingerprint(refreshToken);
        UUID employeeId = this.jwtTokenGenerator.fetchSubjectRefreshToken(refreshToken);
        return this.refreshSessionRepo.findById(sessionId)
                .filter(session -> session.isArgsEq(fingerprint, ip, agent))
                .filter(session -> !session.isExpired())
                .flatMap(session -> this.refreshSessionRepo.save(session.statusOff()))
                .flatMap(session -> this.employeesRepo.findById(employeeId)
                        .flatMap(employee -> collectResponseEntity(employee, session.getIp(), session.getFingerprint(), now, employee.getRoles(), agent)));
    }

    @Override
    public Mono<ResponseEntity<Void>> removeSession(String refreshToken) {
        String sessionId = this.jwtTokenGenerator.fetchRefreshTokenSessionId(refreshToken);
        return this.refreshSessionRepo.findById(sessionId)
                .map(oldSession -> this.refreshSessionRepo.save(oldSession.statusOff()))
                .map(session -> ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("refresh_token", "")
                                .httpOnly(Boolean.TRUE)
                                .secure(Boolean.TRUE)
                                .path("/refresh/sessions")
                                .maxAge(0)
                                .build().toString()
                        ).build()
                );
    }

    private Mono<ResponseEntity<AccessTokenResponse>>
    collectResponseEntity(Employee employee, String ip, String fingerprint, Date now, Set<Role> roles, UserAgent agent) {
        KeysStore keysStore = roles.stream().map(role -> this.keysStore.get(role.getRole()))
                .max(Comparator.comparing(KeysStore::getPriority))
                .orElseThrow();
        RefreshKey refreshKey = keysStore.getRefreshKey();
        var accessToken = this.jwtTokenGenerator.accessToken(employee, keysStore.getAccessKey());
        var expire = new Date(now.getTime() + refreshKey.getExpirationTime());
        return session(employee, refreshKey, ip, fingerprint, expire, accessToken, agent);
    }

    private Mono<ResponseEntity<AccessTokenResponse>>
    session(Employee employee, RefreshKey refreshKey, String ip, String fingerprint, Date expire, String accessToken, UserAgent agent) {
        return this.refreshSessionRepo.save(RefreshSession.create(employee.fetchUuIdAsId(), fingerprint, ip, expire, agent))
                .map(session -> this.jwtTokenGenerator.refreshToken(fingerprint, session.getId(), expire, employee, refreshKey.getId(), refreshKey.getKey()))
                .map(refreshToken -> ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, ResponseCookie.from(refreshKey.getCookieName(), refreshToken)
                                .httpOnly(Boolean.TRUE)
                                .secure(Boolean.TRUE)
                                .path("/refresh/sessions")
                                .maxAge(refreshKey.getExpirationTime() / 1000)
                                .build().toString()
                        ).body(new AccessTokenResponse(accessToken))
                );
    }

}
