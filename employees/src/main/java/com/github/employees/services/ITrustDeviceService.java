package com.github.employees.services;

import com.github.employees.entities.TrustDevice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ITrustDeviceService {

    Mono<TrustDevice> create(TrustDevice data);

    Flux<TrustDevice> readByEmployeeId(UUID employeeId);

    Mono<TrustDevice> readById(String id);

    Mono<Void> update(TrustDevice data);

}
