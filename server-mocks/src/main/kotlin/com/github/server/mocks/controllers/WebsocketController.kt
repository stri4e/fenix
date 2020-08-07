package com.github.server.mocks.controllers

import com.github.server.mocks.payload.OrderDetailEntryDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WebsocketController {

    var log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping(path = ["/v1/push"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun pushOrder(@RequestBody payload: OrderDetailEntryDto?) {
        log.info("Push success {}", payload)
    }

}