package com.github.deliveries.controllers;

import com.github.deliveries.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IAddressController {

    @GetMapping(
            path = "/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AddressDto findAddresses(@PathVariable(name = "addressId") Long addressId);

    @PostMapping(
            path = "/{deliveryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    AddressDto save(@PathVariable(name = "deliveryId") Long deliveryId,
                    @RequestBody AddressDto payload);

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void update(@RequestBody AddressDto payload);

}
