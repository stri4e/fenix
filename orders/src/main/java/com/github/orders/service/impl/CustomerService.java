package com.github.orders.service.impl;

import com.github.orders.dto.CustomerDto;
import com.github.orders.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Override
    public Optional<CustomerDto> readById(Long customerId) {
        return Optional.empty();
    }
}
