package com.github.accounts.controllers;

import com.github.accounts.dto.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface IAccountsController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    AccountDto findAccount(@RequestAttribute UUID userId);

    @PostMapping(
            path = "/edit/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    AccountDto save(@PathVariable UUID userId, @Valid @RequestBody AccountDto payload);

    @PostMapping(
            path = "/default",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    AccountDto saveDefault(@Valid @RequestAttribute UUID userId);


}
