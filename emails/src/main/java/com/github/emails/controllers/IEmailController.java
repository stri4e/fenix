package com.github.emails.controllers;

import com.github.emails.payload.EmailNotification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface IEmailController {

    @PostMapping(
            path = "/submit/reg",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void submitReg(@Valid @RequestBody EmailNotification payload);

    @PostMapping(
            path = "/reset/pass",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void resetPass(@Valid @RequestBody EmailNotification payload);

    @PostMapping(
            path = "/login/notification",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void loginNotification(@Valid @RequestBody EmailNotification payload);

    @PostMapping(
            path = "/registration/orders",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void registrationOrderNotify(@Valid @RequestBody EmailNotification payload);

}
