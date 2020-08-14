package com.github.websocket.controllers;

import com.github.websocket.dto.OrderDto;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PushOrderController {

    @SubscribeMapping(value = "/orders")
    public List<OrderDto> orders() {
        return new ArrayList<>();
    }

}
