package com.github.managers.controllers;

import com.github.managers.dto.OrderDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IOrderDetailController {

    @GetMapping(path = "/pages/staffs/{status}/{staffId}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findStuffOrders(
            @PathVariable(name = "status") String status,
            @PathVariable(name = "staffId") Long staffId,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    OrderDetailDto readById(@RequestParam(name = "orderId") Long orderId);

    @PutMapping(
            path = "/assign/{orderId}/{staffId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void assignManager(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "staffId") Long staffId
    );

    @PutMapping(
            path = "/{orderId}/{orderStatus}"
    )
    void update(@PathVariable(name = "orderId") Long orderId,
                @PathVariable(name = "orderStatus") String orderStatus
    );

}
