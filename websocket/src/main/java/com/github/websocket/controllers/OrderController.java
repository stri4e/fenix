package com.github.websocket.controllers;

import com.github.websocket.payload.OrderDetailEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/push")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void pushOrder(@RequestBody OrderDetailEntry payload) {

    }


}
