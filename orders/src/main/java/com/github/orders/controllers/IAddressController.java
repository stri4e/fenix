package com.github.orders.controllers;

import com.github.orders.dto.AddressDto;
import com.github.orders.entity.AddressType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.UUID;

public interface IAddressController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<AddressDto> findAddresses(@RequestAttribute UUID userId);

    @GetMapping(
            path = "/types",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AddressType[] findAddressType();

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    AddressDto save(@ApiIgnore @RequestAttribute UUID userId,
                    @RequestBody AddressDto payload);

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void update(@RequestBody AddressDto payload);

}
