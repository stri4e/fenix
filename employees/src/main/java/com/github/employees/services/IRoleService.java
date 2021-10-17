package com.github.employees.services;

import com.github.employees.entities.EntityStatus;
import com.github.employees.entities.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRoleService {

    Flux<Role> findAll(EntityStatus status);

    Mono<Role> findById(String id);

    Mono<Role> save(Role role);

    Mono<Void> update(Role role);

}
