package com.github.managers.controllers;

import com.github.managers.payload.SingleMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ISMSController {

    @PostMapping(
            path = "/single/edit"
    )
    void singleSMS(@Valid @RequestBody SingleMessage payload);

}
