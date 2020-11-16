package com.github.orders.service.impl;

import com.github.orders.entity.Customer;
import com.github.orders.exceptions.BadRequest;
import com.github.orders.exceptions.NotFound;
import com.github.orders.repository.CustomerRepo;
import com.github.orders.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer create(Customer o) {
        return this.customerRepo.save(o);
    }

    @Override
    public Customer readById(Long id) {
        return this.customerRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Customer readByUserId(UUID userId) {
        return this.customerRepo.findByUserId(userId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String customerName,
                       String customerEmail, String customerPhone) {
        this.customerRepo.update(id, customerName, customerEmail, customerPhone);
    }

    @Override
    public void update(Customer o) {
        this.customerRepo.save(o);
    }

}
