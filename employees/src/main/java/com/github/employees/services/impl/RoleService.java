package com.github.employees.services.impl;

import com.github.employees.entities.RolePermission;
import com.github.employees.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final Set<RolePermission> roles;

    @Override
    public Flux<RolePermission> findByIds(Set<Long> ids) {
        return Flux.fromStream(this.roles.stream());
    }

}
