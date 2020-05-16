package com.github.admins.controllers;

import com.github.admins.dto.OrderDetailDto;
import com.github.admins.payload.OrderStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IOrderDetailController {

    @GetMapping(
            path = "/all/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<OrderDetailDto>>
    ordersByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<OrderDetailDto> orderById(@PathVariable Long orderId);

    @GetMapping(
            path = "/history/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<OrderDetailDto>>
    userHistory(@PathVariable Long userId);

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Void>
    updateOrder(@Valid @RequestBody OrderDetailDto payload);

    @PutMapping(
            path = "/status/{orderId}/{orderStatus}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Void>
    updateOrderStatus(@PathVariable Long orderId,
                      @PathVariable OrderStatus orderStatus
    );

}
