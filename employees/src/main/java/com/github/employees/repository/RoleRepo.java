package com.github.employees.repository;

import com.github.employees.entities.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends ReactiveMongoRepository<Role, String> {
}
