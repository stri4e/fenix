package com.github.admins.controllers;

import com.github.admins.dto.AccountantDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IAccountantController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    AccountantDto save(@RequestBody AccountantDto payload);

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody AccountantDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(value = "id") Long id);

}
