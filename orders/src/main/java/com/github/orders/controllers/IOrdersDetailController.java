package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.OrderStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.UUID;

public interface IOrdersDetailController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    OrderDetailDto save(
            @ApiIgnore @RequestAttribute(name = "userId") UUID userId,
            @RequestBody @Valid OrderDetailDto payload
    );

    @GetMapping(
            path = "/statuses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    OrderStatus[] findAllOrderStatus();

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    OrderDetailDto findById(@RequestParam(name = "orderId") Long orderId);

    @PutMapping(
            path = "/edit/{orderId}/{orderStatus}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateStatus(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "orderStatus") OrderStatus orderStatus
    );

    @PutMapping(
            path = "/edit/stuff/{orderId}/{staffId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void assignManager(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "staffId") Long staffId
    );

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
