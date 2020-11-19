package com.github.customers.controllers.impl;

import com.github.customers.controllers.ICustomerController;
import com.github.customers.dto.CustomerDto;
import com.github.customers.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.github.customers.utils.TransferObj.fromCustomer;
import static com.github.customers.utils.TransferObj.toCustomer;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;

    @Override
    public CustomerDto findCustomer(UUID userId) {
        return fromCustomer(this.customerService.readByUserId(userId));
    }

    @Override
    public CustomerDto save(UUID userId, CustomerDto payload) {
        return fromCustomer(
                this.customerService.create(toCustomer(payload, userId))
        );
    }

    @Override
    public void updateCustomer(CustomerDto payload) {
        this.customerService.update(
                payload.getId(),
                payload.getCustomerName(),
                payload.getCustomerEmail(),
                payload.getCustomerPhone()
        );
    }

}

