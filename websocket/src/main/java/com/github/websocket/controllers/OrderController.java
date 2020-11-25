package com.github.websocket.controllers;

import com.github.websocket.dto.OrderDetailDto;
import com.github.websocket.network.Broker;
import com.github.websocket.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/push")
@RequiredArgsConstructor
public class OrderController {

    private final Broker broker;

    @PostMapping(path = "/edit")
    @HystrixCommand
    @ResponseStatus(HttpStatus.CREATED)
    @Logging(isTime = true, isReturn = false)
    public void pushOrder(@Valid @RequestBody OrderDetailDto payload) {
        this.broker.sendOrder(payload);
    }

}
