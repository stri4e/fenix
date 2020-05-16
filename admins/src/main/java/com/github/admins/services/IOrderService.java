package com.github.admins.services;

import com.github.admins.payload.OrderDetail;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.impl.OrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "orders-service",
        fallback = OrderService.class
)
public interface IOrderService {

    @GetMapping(
            path = "/orders/v1/{status}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetail> readAllByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "/orders/v1",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    OrderDetail readById(@RequestParam Long id);

    @GetMapping(
            path = "/orders/v1/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetail> readByUserId(@PathVariable Long userId);

    @PutMapping(
            path = "/orders/v1"
    )
    void update(@RequestBody OrderDetail o);

    @PutMapping(
            path = "/orders/v1/{productId}"
    )
    void update(@PathVariable Long productId, @RequestBody OrderStatus orderStatus);

}
