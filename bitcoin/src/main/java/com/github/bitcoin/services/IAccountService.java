package com.github.bitcoin.services;

import com.github.bitcoin.entity.Account;
import com.github.bitcoin.entity.EntityStatus;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IAccountService {

    Page<Account> readAllByStatus(EntityStatus status);

    Account create(Account account);

    Account readByUserId(UUID userId);

    void update(Account account);

}
