package com.github.admins.controllers;

import com.github.admins.dto.OrderDetailDto;
import com.github.admins.payload.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IOrderDetailController {

    @GetMapping(
            path = "/all/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDetailDto> ordersByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    OrderDetailDto orderById(@PathVariable Long orderId);

    @GetMapping(
            path = "/history/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDetailDto> userHistory(@PathVariable Long userId);

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOrder(@Valid @RequestBody OrderDetailDto payload);

    @PutMapping(
            path = "/status/{orderId}/{orderStatus}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOrderStatus(@PathVariable Long orderId,
                           @PathVariable OrderStatus orderStatus
    );

}
