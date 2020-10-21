package com.github.master.card.services.impl;

import com.github.master.card.entity.Account;
import com.github.master.card.entity.EntityStatus;
import com.github.master.card.exceptions.NotFound;
import com.github.master.card.repository.AccountRepo;
import com.github.master.card.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;

    @Override
    public Account readActive() {
        return this.accountRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

}
