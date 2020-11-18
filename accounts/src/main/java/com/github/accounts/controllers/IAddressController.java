package com.github.accounts.controllers;

import com.github.accounts.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IAddressController {

    @GetMapping(
            path = "/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AddressDto findAddress(@PathVariable(name = "addressId") Long addressId);
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    AddressDto save(@Valid @RequestBody AddressDto payload);

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void update(@Valid @RequestBody AddressDto payload);

}
