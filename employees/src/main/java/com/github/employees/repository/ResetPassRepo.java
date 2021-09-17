package com.github.employees.repository;

import com.github.employees.entities.ResetPassToken;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPassRepo extends ReactiveMongoRepository<ResetPassToken, String> {
}
