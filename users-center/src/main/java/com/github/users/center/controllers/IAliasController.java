package com.github.users.center.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

public interface IAliasController {

    @GetMapping(
            path = "/push/{userId}"
    )
    String aliasForPush(@PathVariable(name = "userId") Long userId);

    @GetMapping(
            path = "/listening"
    )
    String aliasForListening(@RequestAttribute Long userId);

}
