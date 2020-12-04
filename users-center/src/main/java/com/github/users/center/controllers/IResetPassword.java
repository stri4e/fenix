package com.github.users.center.controllers;

import com.github.users.center.payload.RenderTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IResetPassword {

    @PostMapping(path = "/edit/{token}")
    @ResponseStatus(code = HttpStatus.OK)
    RenderTemplate resetPass(@PathVariable(name = "token") String token);

}
