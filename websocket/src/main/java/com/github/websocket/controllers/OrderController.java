package com.github.websocket.controllers;

import com.github.websocket.dto.OrderDetailEntry;
import com.github.websocket.utils.Topics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/push")
@RequiredArgsConstructor
public class OrderController {

    private final SimpMessageSendingOperations sendingOperations;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void pushOrder(@RequestBody OrderDetailEntry payload) {
        this.sendingOperations.convertAndSend(Topics.orders.getUrl(), payload);
    }

}
