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

    @GetMapping(path = "/page")
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

    @GetMapping(path = "/fetch/page/{status}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findNewOrders(
            @PathVariable(name = "status") OrderStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(path = "/fetch/managers/page/{status}/{managerId}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findManagerOrders(
            @PathVariable(name = "status") OrderStatus status,
            @PathVariable(name = "managerId") UUID managerId,
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
            @PathVariable(name = "orderStatus") OrderStatus orderStatus);

    @PutMapping(
            path = "/edit/{orderId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void assignManager(
            @PathVariable(name = "orderId") Long orderId,
            @RequestBody UUID managerID);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
