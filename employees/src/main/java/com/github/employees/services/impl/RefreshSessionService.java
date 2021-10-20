package com.github.employees.services.impl;

import com.github.employees.entities.Employee;
import com.github.employees.entities.RefreshSession;
import com.github.employees.entities.Role;
import com.github.employees.models.KeysStore;
import com.github.employees.models.RefreshKey;
import com.github.employees.payload.AccessTokenResponse;
import com.github.employees.repository.RefreshSessionRepo;
import com.github.employees.repository.RoleRepo;
import com.github.employees.services.IRefreshSessionService;
import com.github.employees.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RefreshSessionService implements IRefreshSessionService {

    private final RoleRepo roleRepo;

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshSessionRepo refreshSessionRepo;

    private final Map<String, KeysStore> keysStore;

    @Override
    public Mono<ResponseEntity<AccessTokenResponse>>
    session(Employee employee, String ip, String fingerprint, String userAgent) {
        var now = new Date();
        return Mono.zip(this.refreshSessionRepo.findByEmployeeId(employee.getId())
                        .map(oldSession -> this.refreshSessionRepo.save(oldSession.statusOff())),
                this.roleRepo.findAllById(employee.getRoles()).collectList()
        ).flatMap(tuple -> collectResponseEntity(employee, ip, fingerprint, now, tuple.getT2()));
    }

    private Mono<ResponseEntity<AccessTokenResponse>>
    collectResponseEntity(Employee employee, String ip, String fingerprint, Date now, List<Role> roles) {
        KeysStore keysStore = roles.stream().map(role -> this.keysStore.get(role.getRole()))
                .max(Comparator.comparing(KeysStore::getPriority))
                .orElseThrow();
        RefreshKey refreshKey = keysStore.getRefreshKey();
        var accessToken = this.jwtTokenProvider.accessToken(employee, roles, keysStore.getAccessKey());
        var expire = new Date(now.getTime() + refreshKey.getExpirationTime());
        return session(employee, refreshKey, ip, fingerprint, expire, accessToken);
    }

    private Mono<ResponseEntity<AccessTokenResponse>>
    session(Employee employee, RefreshKey refreshKey, String ip, String fingerprint, Date expire, String accessToken) {
        return this.refreshSessionRepo.save(new RefreshSession(employee.getId(), fingerprint, ip, expire))
                .map(session -> this.jwtTokenProvider.refreshToken(fingerprint, session.getId(), expire, employee, refreshKey.getId(), refreshKey.getKey()))
                .map(refreshToken -> {
                    ResponseCookie cookie = ResponseCookie.from(refreshKey.getCookieName(), refreshToken)
                            .httpOnly(Boolean.TRUE)
                            .secure(Boolean.TRUE)
                            .path("/refresh/sessions")
                            .maxAge(refreshKey.getExpirationTime() / 1000)
                            .build();
                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, cookie.toString())
                            .body(new AccessTokenResponse(accessToken));
                });
    }

}
