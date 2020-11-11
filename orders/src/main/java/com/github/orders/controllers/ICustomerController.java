package com.github.orders.controllers;

import com.github.orders.dto.CustomerDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface ICustomerController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerDto findCustomer(@RequestAttribute UUID userId);

    @PutMapping(
            path = "/edit"
    )
    void updateCustomer(@RequestBody CustomerDto payload);

}
