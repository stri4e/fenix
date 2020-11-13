package com.github.users.center.controllers;

import com.github.users.center.payload.JwtRefreshResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface IRefreshSessionController {

    @PostMapping(
            path = "/admins",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    JwtRefreshResponse refreshSessionAdmins(
            @Valid @RequestBody String refreshToken
    );

    @PostMapping(
            path = "/managers",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    JwtRefreshResponse refreshSessionManagers(
            @NotBlank @RequestBody String refreshToken
    );

}
