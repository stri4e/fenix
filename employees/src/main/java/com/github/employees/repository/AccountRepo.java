package com.github.employees.repository;

import com.github.employees.entities.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface AccountRepo extends ReactiveMongoRepository<Account, String> {
    Mono<Account> findByEmployeeId(UUID employeeId);
}
