package com.github.admins.controllers;

import com.github.admins.dto.OrderDetailDto;
import com.github.admins.payload.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface IOrderDetailController {

    @GetMapping(
            path = "/all/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDetailDto> findByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    OrderDetailDto findById(@PathVariable Long orderId);

    @PutMapping(
            path = "/status/{orderId}/{orderStatus}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOrderStatus(@PathVariable Long orderId,
                           @PathVariable OrderStatus orderStatus
    );

}
