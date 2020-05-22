package com.github.server.mocks.controllers

import com.github.server.mocks.payload.ConfirmEmail
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailControllers {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/v1/submit/reg")
    @ResponseStatus(HttpStatus.OK)
    fun submitReg(@RequestBody payload : ConfirmEmail) {
        logger.info("Enter: {}", payload)
    }

    @PostMapping("/v1/reset/pass")
    @ResponseStatus(HttpStatus.OK)
    fun resetPass(@RequestBody payload : ConfirmEmail) {
        logger.info("Enter: {}", payload)
    }

}