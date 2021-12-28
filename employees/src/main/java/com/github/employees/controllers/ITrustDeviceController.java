package com.github.employees.controllers;

import com.github.employees.payload.TrustDeviceDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ITrustDeviceController {

    @PostMapping
    Mono<TrustDeviceDto> save(
            @RequestAttribute(name = "employeeId") UUID employeeId,
            @RequestBody TrustDeviceDto payload
    );

    @GetMapping
    Flux<TrustDeviceDto> readAll(@RequestAttribute(name = "employeeId") UUID employeeId);

    @DeleteMapping(path = "/edit/{id}")
    Mono<Void> delete(@PathVariable(name = "id") String id);

}
