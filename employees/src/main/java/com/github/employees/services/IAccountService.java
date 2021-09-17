package com.github.employees.services;

import com.github.employees.entities.Account;
import reactor.core.publisher.Mono;

public interface IAccountService {

    Mono<Void> createDefaultAccount(Account account);

}
