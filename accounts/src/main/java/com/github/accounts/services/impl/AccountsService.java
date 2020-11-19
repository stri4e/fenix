package com.github.accounts.services.impl;

import com.github.accounts.entity.Account;
import com.github.accounts.entity.EntityStatus;
import com.github.accounts.exceptions.NotFound;
import com.github.accounts.repository.AccountsRepo;
import com.github.accounts.services.IAccountsService;
import com.github.accounts.utils.AccountSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.github.accounts.utils.AccountSpec.byEmailAndPhone;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountsService implements IAccountsService {

    private final AccountsRepo accountsRepo;

    @Override
    public Account create(Account account) {
        return this.accountsRepo.save(account);
    }

    @Override
    public Account readByUserId(UUID userId) {
        return this.accountsRepo.findByUserId(userId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Account readById(Long id) {
        return this.accountsRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Account readByEmailAndPhone(String email, String phone) {
        return this.accountsRepo.findOne(byEmailAndPhone(email, phone))
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Account account) {
        this.accountsRepo.save(account);
    }

    @Override
    public void remove(Long id) {
        this.accountsRepo.delete(id, EntityStatus.off);
    }
}
