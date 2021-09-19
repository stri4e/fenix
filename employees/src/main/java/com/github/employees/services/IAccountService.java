package com.github.employees.services;

import com.github.employees.entities.Account;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IAccountService {

    Mono<Void> createDefaultAccount(Account account);

    Mono<Account> create(Account account);

    Mono<Account> readByEmployeeId(UUID employeeId);

    Mono<Void> update(Account account);

}
