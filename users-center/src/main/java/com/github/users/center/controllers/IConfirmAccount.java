package com.github.users.center.controllers;

import com.github.users.center.payload.RenderTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface IConfirmAccount {

    @PostMapping(path = "/edit/{token}")
    RenderTemplate confirmAccount(@PathVariable(name = "token") String token);

}
