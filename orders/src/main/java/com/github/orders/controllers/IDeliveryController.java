package com.github.orders.controllers;

import com.github.orders.dto.DeliveryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

public interface IDeliveryController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    DeliveryDto findDelivery(@RequestAttribute UUID userId);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    DeliveryDto save(@ApiIgnore @RequestAttribute UUID userId,
                     @RequestBody DeliveryDto payload);

    @PutMapping
    void updateDelivery(@RequestBody DeliveryDto payload);

}
