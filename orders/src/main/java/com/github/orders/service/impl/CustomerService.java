package com.github.orders.service.impl;

import com.github.orders.entity.Customer;
import com.github.orders.exceptions.BadRequest;
import com.github.orders.exceptions.TypeMessage;
import com.github.orders.repository.CustomerRepo;
import com.github.orders.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer create(Customer o) {
        if (Objects.isNull(o)) {
            throw new BadRequest(
                    TypeMessage.badOrderData
            );
        }
        return this.customerRepo.save(o);
    }

}
