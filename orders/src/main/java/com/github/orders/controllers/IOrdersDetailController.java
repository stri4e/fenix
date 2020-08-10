package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IOrdersDetailController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    void save(
            @RequestAttribute(name = "userId") Long userId,
            @RequestBody @Valid OrderDetailDto payload
    );

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetail> findAllByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    OrderDetail readById(@RequestParam(name = "orderId") Long orderId);

    @PutMapping(
            path = "/edit"
    )
    void update(@RequestBody OrderDetail o);

    @PutMapping(
            path = "/edit/{orderId}/{orderStatus}"
    )
    void update(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "orderStatus") OrderStatus orderStatus);

}
