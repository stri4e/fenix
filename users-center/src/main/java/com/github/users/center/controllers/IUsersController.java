package com.github.users.center.controllers;

import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.payload.JwtAuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IUsersController {

    @PostMapping(path = "/reg")
    ResponseEntity<Void> submitReg(
            @RequestHeader(name = "Client-Address") String userUrl,
            @Valid @RequestBody UserRegDto payload
    );

    @GetMapping(path = "/confirm-account")
    ResponseEntity<Void> confirmAccount(@RequestParam String token);

    @PostMapping(path = "/auth")
    ResponseEntity<JwtAuthResponse> submitAuth(@Valid @RequestBody UserAuthDto payload);

    @PostMapping(path = "/forgot-pass")
    ResponseEntity<Void> processForgotPass(@Valid  @RequestBody ForgotPassDto payload);

    @DeleteMapping(path = "/reset-pass")
    ResponseEntity<Void> resetPass(@RequestParam String token);

}
