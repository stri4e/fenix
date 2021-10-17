package com.github.employees.controllers.impl;

import com.github.employees.controllers.IRoleController;
import com.github.employees.entities.EntityStatus;
import com.github.employees.payload.RoleDto;
import com.github.employees.services.IRoleService;
import com.github.employees.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/roles")
public class RoleController implements IRoleController {

    private final IRoleService roleService;

    @Override
    public Flux<RoleDto> findAll(EntityStatus status) {
        return this.roleService.findAll(status)
                .map(TransferObj::fromRole);
    }

    @Override
    public Mono<RoleDto> save(RoleDto payload) {
        return this.roleService.save(TransferObj.toRole(payload))
                .map(TransferObj::fromRole);
    }

    @Override
    public Mono<Void> update(RoleDto payload) {
        return this.roleService.findById(payload.getId())
                .flatMap(role -> this.roleService.update(role.role(payload.getRole())));
    }

    @Override
    public Mono<Void> updateStatus(EntityStatus status, String id) {
        return this.roleService.findById(id)
                .flatMap(role -> this.roleService.update(role.status(status)));
    }

}
