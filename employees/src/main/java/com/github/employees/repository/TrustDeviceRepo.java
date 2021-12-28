package com.github.employees.repository;

import com.github.employees.entities.TrustDevice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TrustDeviceRepo extends ReactiveMongoRepository<TrustDevice, UUID> {

    Flux<TrustDevice> findByEmployeeId(UUID employeeId);

    Mono<TrustDevice> findById(String id);

}
