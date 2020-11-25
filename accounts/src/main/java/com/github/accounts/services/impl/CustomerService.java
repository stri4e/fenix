package com.github.accounts.services.impl;

import com.github.accounts.dto.CustomerDto;
import com.github.accounts.services.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService implements ICustomerService {
    @Override
    public CustomerDto save(UUID userId, CustomerDto payload) {
        return null;
    }
}
