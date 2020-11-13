package com.github.users.center.controllers;

import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.payload.JwtAuthResponse;
import com.github.users.center.payload.RenderTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

public interface IUsersController {

    @PostMapping(path = "/reg")
    @ResponseStatus(code = HttpStatus.CREATED)
    void submitReg(@ApiIgnore @RequestHeader(name = "Origin") String origin,
                   @Valid @RequestBody UserRegDto payload
    );

    @PostMapping(path = "/edit/confirm-account/{token}")
    RenderTemplate confirmAccount(@PathVariable(name = "token") String token);

    @PostMapping(path = "/auth")
    @ResponseStatus(code = HttpStatus.OK)
    JwtAuthResponse submitAuth(
            @ApiIgnore @RequestAttribute(name = "ip") String ip,
            @ApiIgnore @RequestHeader(name = "User-Agent") String userAgent,
            @Valid @RequestBody UserAuthDto payload
    );

    @PostMapping(path = "/forgot-pass")
    @ResponseStatus(code = HttpStatus.OK)
    void processForgotPass(
            @ApiIgnore @RequestHeader(name = "Origin") String origin,
            @Valid @RequestBody ForgotPassDto payload
    );

    @PostMapping(path = "/edit/reset-pass/{token}")
    @ResponseStatus(code = HttpStatus.OK)
    RenderTemplate resetPass(@PathVariable(name = "token") String token);

}
