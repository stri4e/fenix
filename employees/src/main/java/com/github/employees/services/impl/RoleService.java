package com.github.employees.services.impl;

import com.github.employees.entities.EntityStatus;
import com.github.employees.entities.Role;
import com.github.employees.repository.RoleRepo;
import com.github.employees.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepo roleRepo;

    @Override
    public Flux<Role> findAll(EntityStatus status) {
        return this.roleRepo.findByStatus(status);
    }

    @Override
    public Mono<Role> findById(String id) {
        return this.roleRepo.findById(id);
    }

    @Override
    public Mono<Role> save(Role role) {
        return this.roleRepo.save(role);
    }

    @Override
    public Mono<Void> update(Role role) {
        return this.roleRepo.save(role).then();
    }

}
