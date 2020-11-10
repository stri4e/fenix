package com.github.users.center.controllers;

import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.payload.ConfirmReport;
import com.github.users.center.payload.JwtAuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

public interface IUsersController {

    @PostMapping(path = "/reg")
    @ResponseStatus(code = HttpStatus.CREATED)
    void submitReg(@ApiIgnore @RequestHeader(name = "Origin") String clientUrl,
                   @Valid @RequestBody UserRegDto payload
    );

    @PostMapping(path = "/edit/confirm-account/{token}")
    ConfirmReport confirmAccount(@PathVariable(name = "token") String token);

    @PostMapping(path = "/auth")
    @ResponseStatus(code = HttpStatus.OK)
    JwtAuthResponse submitAuth(
            @ApiIgnore @RequestHeader(name = "X-Forwarded-For") String location,
            @ApiIgnore @RequestHeader(name = "User-Agent") String device,
            @Valid @RequestBody UserAuthDto payload
    );

    @PostMapping(path = "/forgot-pass")
    @ResponseStatus(code = HttpStatus.OK)
    void processForgotPass(
            @ApiIgnore @RequestHeader(name = "Origin") String clientUrl,
            @Valid @RequestBody ForgotPassDto payload
    );

    @PostMapping(path = "/edit/reset-pass/{token}")
    @ResponseStatus(code = HttpStatus.OK)
    ConfirmReport resetPass(@PathVariable(name = "token") String token);

}
