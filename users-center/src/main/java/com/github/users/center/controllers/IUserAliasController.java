package com.github.users.center.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.UUID;

public interface IUserAliasController {

    @GetMapping(
            path = "/fetch/push/{userId}"
    )
    String aliasForPush(@PathVariable(name = "userId") UUID userId);

    @GetMapping(
            path = "/listening"
    )
    String aliasForListening(@RequestAttribute UUID userId);

}
