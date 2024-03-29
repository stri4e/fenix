package com.github.admins.services;

import com.github.admins.dto.OrderDetailDto;
import com.github.admins.services.impl.OrdersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "orders",
        fallback = OrdersService.class,
        contextId = "orderId"
)
public interface IOrdersService {

    @GetMapping(
            path = "/v1/pages/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<Page<OrderDetailDto>> readByStatus(@PathVariable String status, Pageable pageable);

    @GetMapping(
            path = "/v1/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<OrderDetailDto> readById(@RequestParam(name = "orderId") Long orderId);

    @PutMapping(
            path = "/v1/edit/{productId}/{status}"
    )
    void update(
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "status") String status
    );

}
