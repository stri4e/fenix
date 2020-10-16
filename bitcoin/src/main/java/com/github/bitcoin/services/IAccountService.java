package com.github.bitcoin.services;

import com.github.bitcoin.entity.Account;
import com.github.bitcoin.entity.EntityStatus;
import org.springframework.data.domain.Page;

public interface IAccountService {

    Page<Account> readAllByStatus(EntityStatus status);

    Account create(Account account);

    Account readByUserId(Long userId);

    void update(Account account);

}
