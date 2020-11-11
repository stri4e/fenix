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
import java.util.List;
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

    @GetMapping
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDetailDto> findUserOrders(
            @ApiIgnore @RequestAttribute(name = "userId") UUID userId
    );

    @GetMapping(path = "/fetch/binding/{orderId}")
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDetailDto> findBindingOrders(
            @PathVariable(name = "orderId") Long orderId
    );

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDetailDto> findAllByStatus(
            @PathVariable OrderStatus status,
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end
    );

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Object findByParams(@RequestParam(name = "orderId", required = false) Long orderId,
                        @RequestParam(name = "ids", required = false) List<Long> ids);

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOrder(@RequestBody OrderDetailDto o);

    @PutMapping(
            path = "/edit/{orderId}/{orderStatus}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOderStatus(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "orderStatus") OrderStatus orderStatus);

    @PutMapping(
            path = "/edit/paid/{billId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOderPaid(@PathVariable(name = "billId") Long billId);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
