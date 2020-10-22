package com.github.payments.controllers;

import com.github.payments.dto.AccountantDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IAccountantController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AccountantDto save(AccountantDto payload);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AccountantDto findActive();

    @PutMapping(
            path = "/edit"
    )
    void update(AccountantDto payload);

    @DeleteMapping(
            path = "/edit"
    )
    void remove(Long id);

}
