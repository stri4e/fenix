package com.github.accounts.controllers;

import com.github.accounts.dto.ContactDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IContactController {

    @GetMapping(
            path = "/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ContactDto findContact(@PathVariable Long contactId);

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    ContactDto save(@Valid @RequestBody ContactDto payload);

    @PutMapping
    void update(@Valid @RequestBody ContactDto payload);

}
