package com.github.employees.services.impl;

import com.github.employees.entities.Account;
import com.github.employees.repository.AccountRepo;
import com.github.employees.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;

    @Override
    public Mono<Void> createDefaultAccount(Account account) {
        return this.accountRepo.save(account).then();
    }

    @Override
    public Mono<Account> create(Account account) {
        return this.accountRepo.save(account);
    }

    @Override
    public Mono<Account> readByEmployeeId(UUID employeeId) {
        return this.accountRepo.findByEmployeeId(employeeId);
    }

    @Override
    public Mono<Void> update(Account account) {
        return this.accountRepo.save(account).then();
    }

}
