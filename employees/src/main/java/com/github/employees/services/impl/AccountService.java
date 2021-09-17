package com.github.employees.services.impl;

import com.github.employees.entities.Account;
import com.github.employees.repository.AccountRepo;
import com.github.employees.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;

    @Override
    public Mono<Void> createDefaultAccount(Account account) {
        return this.accountRepo.save(account).then();
    }

}
