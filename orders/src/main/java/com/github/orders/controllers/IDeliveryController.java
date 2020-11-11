package com.github.orders.controllers;

import com.github.orders.dto.DeliveryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface IDeliveryController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    DeliveryDto findDelivery(@RequestAttribute UUID userId);

    @PutMapping(
            path = "/edit"
    )
    void updateDelivery(@RequestBody DeliveryDto payload);

}
