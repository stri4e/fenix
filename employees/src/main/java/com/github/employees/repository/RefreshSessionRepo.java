package com.github.employees.repository;

import com.github.employees.entities.RefreshSession;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshSessionRepo extends ReactiveMongoRepository<RefreshSession, String> {
}
