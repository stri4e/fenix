package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.OrderStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

public interface IOrdersDetailPaginationController {

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
    Page<OrderDetailDto> findUserOrders(
            @ApiIgnore @RequestAttribute(name = "userId") UUID userId,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(path = "/fetch/{status}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findNewOrders(
            @PathVariable(name = "status") OrderStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(path = "/fetch/staffs/{status}/{staffId}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findStuffOrders(
            @PathVariable(name = "status") OrderStatus status,
            @PathVariable(name = "staffId") Long staffId,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(path = "/fetch/unassigned/page")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> unassignedOrders(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(path = "/fetch/customers/{customerId}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findOrdersByCustomer(
            @PathVariable(name = "customerId") Long customerId,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

}
