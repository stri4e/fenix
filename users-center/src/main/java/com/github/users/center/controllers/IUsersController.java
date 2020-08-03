package com.github.users.center.controllers;

import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.payload.JwtAuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IUsersController {

    @PostMapping(path = "/reg")
    @ResponseStatus(code = HttpStatus.CREATED)
    void submitReg(@RequestHeader(name = "Client-Address") String userUrl,
                   @Valid @RequestBody UserRegDto payload
    );

    @GetMapping(path = "/confirm-account")
    ResponseEntity<Void> confirmAccount(@RequestParam String token);

    @PostMapping(path = "/auth")
    @ResponseStatus(code = HttpStatus.OK)
    JwtAuthResponse submitAuth(
            @RequestHeader(name = "Location") String location,
            @RequestHeader(name = "User-Agent") String userInfo,
            @Valid @RequestBody UserAuthDto payload
    );

    @PostMapping(path = "/forgot-pass")
    @ResponseStatus(code = HttpStatus.OK)
    void processForgotPass(@Valid @RequestBody ForgotPassDto payload);

    @DeleteMapping(path = "/reset-pass")
    @ResponseStatus(code = HttpStatus.OK)
    void resetPass(@RequestParam String token);

}
