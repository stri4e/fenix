package com.github.employees.repository;

import com.github.employees.entities.RefreshSession;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface RefreshSessionRepo extends ReactiveMongoRepository<RefreshSession, String> {
    Mono<RefreshSession> findByEmployeeId(UUID employeeId);
}
