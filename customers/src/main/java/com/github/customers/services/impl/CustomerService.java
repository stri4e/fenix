package com.github.customers.services.impl;

import com.github.customers.entity.Customer;
import com.github.customers.exceptions.NotFound;
import com.github.customers.repository.CustomerRepo;
import com.github.customers.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void update(Long id, String firstName, String lastName,
                       String customerEmail, String customerPhone) {
        this.customerRepo.update(id, firstName, lastName, customerEmail, customerPhone);
    }

    @Override
    public void update(Customer o) {
        this.customerRepo.save(o);
    }

}
