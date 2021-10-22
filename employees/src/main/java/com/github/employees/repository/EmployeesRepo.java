package com.github.employees.repository;

import com.github.employees.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface EmployeesRepo extends ReactiveMongoRepository<Employee, UUID> {

    Mono<Boolean> existsByEmailOrLogin(String email, String login);

    Mono<Employee> findByEmailOrLogin(String email, String login);

    Mono<Employee> findByEmail(String email);

    Flux<Employee> findByLockedIs(boolean isLocked, Pageable pageable);

}
