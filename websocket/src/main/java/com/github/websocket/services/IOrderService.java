package com.github.websocket.services;

import com.github.websocket.payload.OrderDetail;
import com.github.websocket.payload.OrderStatus;
import com.github.websocket.services.impl.OrderService;
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

}
