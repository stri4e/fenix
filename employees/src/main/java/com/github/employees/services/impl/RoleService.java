package com.github.employees.services.impl;

import com.github.employees.entities.Role;
import com.github.employees.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final Set<Role> roles;

    @Override
    public Flux<Role> findByIds(Set<Long> ids) {
        return Flux.fromStream(ids.stream()
                .flatMap(roleId -> this.roles.stream()
                        .filter(role -> roleId.equals(role.getId())))
        );
    }

}
