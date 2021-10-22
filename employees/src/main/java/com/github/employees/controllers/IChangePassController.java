package com.github.employees.controllers;

import com.github.employees.payload.ChangePassword;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface IChangePassController {

    @PostMapping
    Mono<Void> resetPassword(
            @RequestAttribute(name = "ip") String ip,
            @RequestHeader(name = "User-Agent") String userAgent,
            @Valid @RequestBody ChangePassword payload
    );

}
