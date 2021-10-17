package com.github.employees.controllers;

import com.github.employees.entities.EntityStatus;
import com.github.employees.payload.AccountDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IAccountController {

    @GetMapping
    Mono<AccountDto>
    findById(@RequestAttribute(name = "employeeId") UUID employeeId);

    @PostMapping(path = "/{employeeId}")
    Mono<AccountDto> save(
            @PathVariable(name = "employeeId") UUID employeeId,
            @RequestBody AccountDto payload
    );

    @PutMapping(path = "/status/{employeeId}/{status}")
    Mono<Void> updateStatus(
            @PathVariable(name = "employeeId") UUID employeeId,
            @PathVariable(name = "status") EntityStatus status
    );

    @PutMapping(path = "/{employeeId}")
    Mono<Void> updateAccount(@PathVariable(name = "employeeId") UUID employeeId,
                             @RequestBody AccountDto payload);

}
