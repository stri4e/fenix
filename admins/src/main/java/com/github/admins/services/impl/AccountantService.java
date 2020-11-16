package com.github.admins.services.impl;

import com.github.admins.dto.AccountantDto;
import com.github.admins.services.IAccountantService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountantService implements IAccountantService {

    @Override
    public Optional<AccountantDto> save(AccountantDto payload) {
        return Optional.empty();
    }

    @Override
    public void update(AccountantDto payload) {

    }

    @Override
    public void remove(Long id) {

    }
}
