package com.github.orders.service;

import com.github.orders.entity.Customer;

import java.util.UUID;

public interface ICustomerService {

    Customer createOrUpdate(Customer o);

    Customer readById(Long id);

    Customer readByUserId(UUID userId);

    void update(
            Long id,
            String customerName,
            String customerAddress,
            String customerEmail,
            String customerPhone
    );

}
