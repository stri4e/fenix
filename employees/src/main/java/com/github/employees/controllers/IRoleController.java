package com.github.employees.controllers;

import com.github.employees.entities.EntityStatus;
import com.github.employees.payload.RoleDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRoleController {

    @GetMapping(path = "/{status}")
    Flux<RoleDto> findAll(@PathVariable(name = "status") EntityStatus status);

    @PostMapping
    Mono<RoleDto> save(@RequestBody RoleDto payload);

    @PutMapping
    Mono<Void> update(@RequestBody RoleDto payload);

    @PutMapping(path = "/status/{status}/by/{id}")
    Mono<Void> updateStatus(
            @PathVariable(name = "status") EntityStatus status,
            @PathVariable(name = "id") String id
    );

}
