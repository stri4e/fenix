package com.github.employees.repository;

import com.github.employees.entities.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends ReactiveMongoRepository<Account, String> {
}
