package com.github.customers.services;

import com.github.customers.entity.Customer;

import java.util.UUID;

public interface ICustomerService {

    Customer create(Customer o);

    Customer readById(Long id);

    Customer readByUserId(UUID userId);

    void update(
            Long id,
            String customerName,
            String customerEmail,
            String customerPhone
    );

    void update(Customer o);

}
