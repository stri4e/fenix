package com.github.users.center.services.impl;

import com.github.users.center.dto.AccountDto;
import com.github.users.center.services.IAccountsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountsService implements IAccountsService {
    @Override
    public void createAccount(UUID userId, AccountDto payload) {

    }
}
