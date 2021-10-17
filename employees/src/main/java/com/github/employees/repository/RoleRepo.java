package com.github.employees.repository;

import com.github.employees.entities.EntityStatus;
import com.github.employees.entities.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RoleRepo extends ReactiveMongoRepository<Role, String> {

    Flux<Role> findByStatus(EntityStatus status);

}
