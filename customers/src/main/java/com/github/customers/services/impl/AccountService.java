package com.github.customers.services.impl;

import com.github.customers.dto.CustomerDto;
import com.github.customers.services.IAccountService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.UUID;

@Service
public class AccountService implements IAccountService {
    @Override
    public void updateByCustomer(UUID userId, @Valid CustomerDto payload) {

    }
}
