package com.github.employees.services;

import com.github.employees.entities.Role;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface IRoleService {

    Flux<Role> findByIds(Set<Long> ids);

}
