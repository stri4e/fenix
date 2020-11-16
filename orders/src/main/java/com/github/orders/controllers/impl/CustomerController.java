package com.github.orders.controllers.impl;

import com.github.orders.controllers.ICustomerController;
import com.github.orders.dto.CustomerDto;
import com.github.orders.entity.Address;
import com.github.orders.service.IAddressService;
import com.github.orders.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.github.orders.utils.TransferObj.fromCustomer;
import static com.github.orders.utils.TransferObj.toCustomer;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/customers")
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;

    private final IAddressService addressService;

    @Override
    public CustomerDto findCustomer(UUID userId) {
        return fromCustomer(this.customerService.readByUserId(userId));
    }

    @Override
    public CustomerDto save(UUID userId, CustomerDto payload) {
        Address address = this.addressService.readById(payload.getCustomerAddress().getId());
        return fromCustomer(
                this.customerService.create(toCustomer(payload, userId).address(address))
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

