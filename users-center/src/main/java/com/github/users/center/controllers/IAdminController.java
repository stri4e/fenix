package com.github.users.center.controllers;

import com.github.users.center.dto.LockedDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.payload.JwtRefreshResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

public interface IAdminController {

    @PostMapping(path = "/edit/reg")
    @ResponseStatus(code = HttpStatus.CREATED)
    void submitReg(
            @ApiIgnore @RequestHeader(name = "Origin") String clientUrl,
            @Valid @RequestBody UserRegDto payload
    );

    @PostMapping(path = "/auth")
    @ResponseStatus(code = HttpStatus.OK)
    JwtRefreshResponse submitAuth(
            @RequestHeader(name = "ETag") String fingerprint,
            @ApiIgnore @RequestHeader(name = "X-Forwarded-For") String location,
            @ApiIgnore @RequestHeader(name = "User-Agent") String device,
            @Valid @RequestBody UserAuthDto payload
    );

    @PostMapping(
            path = "/refresh-tokens",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    JwtRefreshResponse submitRefreshSession(
            @Valid @RequestBody String refreshToken
    );

    @PutMapping(
            path = "/edit/locked"
    )
    void lockedUser(@RequestBody LockedDto payload);

}
