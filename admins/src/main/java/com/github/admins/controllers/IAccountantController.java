package com.github.admins.controllers;

import com.github.admins.dto.AccountantDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IAccountantController {

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AccountantDto save(AccountantDto payload);

    @PutMapping(
            path = "/"
    )
    void update(AccountantDto payload);

    @DeleteMapping(
            path = "/"
    )
    void remove(Long id);

}
