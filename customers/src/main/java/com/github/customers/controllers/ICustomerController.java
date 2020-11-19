package com.github.customers.controllers;

import com.github.customers.dto.CustomerDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

public interface ICustomerController {

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto findCustomer(@RequestAttribute UUID userId);

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    CustomerDto save(@ApiIgnore @RequestAttribute UUID userId,
                     @RequestBody CustomerDto payload);

    @PutMapping
    void updateCustomer(@RequestBody CustomerDto payload);

}
