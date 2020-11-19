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
            path = "/v1/page/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<Page<OrderDetailDto>> readByStatus(@PathVariable String status, Pageable pageable);

    @GetMapping(
            path = "/v1/fetch/{status}/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<OrderDetailDto>> findByStatusInTime(
            @PathVariable String status,
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end
    );

    @GetMapping(
            path = "/v1/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<OrderDetailDto> readById(@RequestParam(name = "orderId") Long orderId);

    @PutMapping(
            path = "/v1/edit/{productId}/{orderStatus}"
    )
    void update(@PathVariable Long productId, @PathVariable String orderStatus);

}
