package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.OrderDto;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

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
    void saveOrders(
            @ApiIgnore @RequestAttribute(name = "userId") Long userId,
            @RequestBody @Valid OrderDetailDto payload
    );

    @GetMapping(path = "/fetch/{status}/time")
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDto> fetchOrdersInTime(
            @PathVariable OrderStatus status,
            @RequestParam("start") LocalDateTime start,
            @RequestParam("end") LocalDateTime end
    );

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderDto> findAllByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Object findByParams(@RequestParam(name = "orderId") Long orderId,
                        @RequestParam(name = "ids") List<Long> ids);

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOrder(@RequestBody OrderDetail o);

    @PutMapping(
            path = "/edit/{orderId}/{orderStatus}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateOderStatus(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "orderStatus") OrderStatus orderStatus);

}
