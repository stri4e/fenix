package com.github.server.mocks.controllers

import com.github.server.mocks.payload.OrderDetailEntry
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/push"])
class OrderController {

    @PostMapping
    fun pushOrder(@RequestBody payload: OrderDetailEntry) {

    }

}