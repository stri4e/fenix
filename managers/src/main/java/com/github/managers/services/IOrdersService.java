package com.github.managers.services;

import com.github.managers.dto.OrderDetailDto;
import com.github.managers.services.impl.OrdersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "orders",
        fallback = OrdersService.class,
        contextId = "orderId"
)
public interface IOrdersService {

    @GetMapping(path = "/v1/pages/fetch/staffs/{status}/{staffId}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findStuffOrders(
            @PathVariable(name = "status") String status,
            @PathVariable(name = "staffId") Long staffId,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(path = "/v1/pages/fetch/customers/{customerId}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<OrderDetailDto> findCustomerOrders(
            @PathVariable(name = "customerId") Long customerId,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.DESC),
            }) Pageable pageable
    );

    @GetMapping(
            path = "/v1/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<OrderDetailDto> readById(@RequestParam(name = "orderId") Long orderId);

    @PutMapping(
            path = "/v1/edit/stuff/{orderId}/{staffId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void assignManager(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "staffId") Long staffId
    );

    @PutMapping(
            path = "/v1/edit/{orderId}/{status}"
    )
    void update(@PathVariable(name = "orderId") Long orderId,
                @PathVariable(name = "status") String status
    );

}
