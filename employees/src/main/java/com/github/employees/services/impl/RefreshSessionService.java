package com.github.employees.services.impl;

import com.github.employees.entities.Employee;
import com.github.employees.payload.JwtRefreshResponse;
import com.github.employees.repository.RefreshSessionRepo;
import com.github.employees.repository.RoleRepo;
import com.github.employees.services.IRefreshSessionService;
import com.github.employees.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RefreshSessionService implements IRefreshSessionService {

    private final RoleRepo roleRepo;

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshSessionRepo refreshSessionRepo;

    @Override
    public Mono<JwtRefreshResponse>
    session(Employee employee, String ip, String fingerprint, String userAgent) {
        return this.roleRepo.findAllById(employee.getRoles()).collectList()
                .flatMap(roles -> this.refreshSessionRepo.save(
                                this.jwtTokenProvider.refreshSession(fingerprint, ip, employee, roles)
                        ).map(rs -> new JwtRefreshResponse(
                                        this.jwtTokenProvider.accessToken(employee, roles),
                                        rs.getRefreshToken(),
                                        rs.expireIn()
                                )
                        )
                );
    }

}
