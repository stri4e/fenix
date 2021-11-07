package com.github.employees.services;

import com.github.employees.entities.RolePermission;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    Flux<RolePermission> findByIds(Set<Long> ids);

}
