package com.github.orders.controllers;

import com.github.orders.dto.OrderDto;
import com.github.orders.entity.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IManagersOrdersController {

    @PostMapping(
            path = "/{orderId}/{orderStatus}"
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void save(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "orderStatus") OrderStatus orderStatus,
            @RequestAttribute(name = "managerId") Long mangerId,
            @RequestAttribute(name = "firstName") String firstName,
            @RequestAttribute(name = "lastName") String lastName
    );

    @PutMapping(
            path = "/{orderId}/{orderStatus}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "orderStatus") OrderStatus orderStatus
    );

    @GetMapping(
            path = "/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDto> findAllOpenOrders(
            @RequestAttribute(name = "managerId") Long managerId,
            @PathVariable OrderStatus status
    );

}
