package com.github.orders.controllers;

import com.github.orders.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

public interface ICustomerController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto findCustomer(@RequestAttribute UUID userId);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    CustomerDto save(@ApiIgnore @RequestAttribute UUID userId,
                     @RequestBody CustomerDto payload);

    @PutMapping
    void updateCustomer(@RequestBody CustomerDto payload);

}
