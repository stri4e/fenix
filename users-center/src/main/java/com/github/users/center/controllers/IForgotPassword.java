package com.github.users.center.controllers;

import com.github.users.center.dto.ForgotPassDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

public interface IForgotPassword {

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    void usersForgotPass(
            @ApiIgnore @RequestHeader(name = "Origin") String origin,
            @Valid @RequestBody ForgotPassDto payload
    );

    @PostMapping(path = "/edit")
    @ResponseStatus(code = HttpStatus.OK)
    void staffForgotPass(
            @ApiIgnore @RequestHeader(name = "Origin") String origin,
            @Valid @RequestBody ForgotPassDto payload
    );

}
