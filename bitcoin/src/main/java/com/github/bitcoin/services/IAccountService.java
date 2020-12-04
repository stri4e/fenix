package com.github.bitcoin.services;

import com.github.bitcoin.entity.Account;
import com.github.bitcoin.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAccountService {

    Page<Account> readAllByStatus(EntityStatus status, Pageable pageable);

    Account create(Account account);

    Account readByUserId(UUID userId);

    void update(Account account);

}
