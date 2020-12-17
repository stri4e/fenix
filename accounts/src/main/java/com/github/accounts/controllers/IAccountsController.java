package com.github.accounts.controllers;

import com.github.accounts.dto.AccountDto;
import com.github.accounts.dto.CustomerDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

public interface IAccountsController {

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
    AccountDto findAccount(@RequestAttribute UUID userId);

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AccountDto findByParams(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "phone") String phone
    );

    @PostMapping(
            path = "/edit/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    AccountDto save(@PathVariable UUID userId, @Valid @RequestBody AccountDto payload);

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @PostMapping(
            path = "/default",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    AccountDto saveDefault(@Valid @RequestAttribute UUID userId);

    @PutMapping(
            path = "/edit/{userId}"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void updateByCustomer(@PathVariable UUID userId, @Valid @RequestBody CustomerDto payload);

}
