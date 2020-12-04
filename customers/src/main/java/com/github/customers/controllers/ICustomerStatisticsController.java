package com.github.customers.controllers;

import com.github.customers.dto.CustomerStatisticsDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface ICustomerStatisticsController {

    @GetMapping(
            path = "/fetch/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerStatisticsDto findById(@PathVariable(name = "customerId") Long customerId);

    @PutMapping(
            path = "/edit/total/orders/{customerId}/{totalOrders}"
    )
    void updateTotalOrders(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "totalOrders") Integer totalOrders

    );

    @PutMapping(
            path = "/edit/success/orders/{customerId}/{successOrders}"
    )
    void updateSuccessOrders(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "successOrders") Integer successOrders
    );

    @PutMapping(
            path = "/edit/returned/orders/{customerId}/{returnedOrders}"
    )
    void updateReturnedOrders(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "returnedOrders") Integer returnedOrders
    );

}
