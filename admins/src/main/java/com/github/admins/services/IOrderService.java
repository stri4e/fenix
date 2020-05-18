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
            path = "/v1/info/{status}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetail> readAllByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "/v1/info/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    OrderDetail readById(@RequestParam Long id);

    @GetMapping(
            path = "/v1/info/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetail> readByUserId(@PathVariable Long userId);

    @PutMapping(
            path = "/v1/edit/"
    )
    void update(@RequestBody OrderDetail o);

    @PutMapping(
            path = "/v1/edit/{productId}/{orderStatus}"
    )
    void update(@PathVariable Long productId, @PathVariable OrderStatus orderStatus);

}
