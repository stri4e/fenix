package com.github.emails.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ISuccessErrorPagesController {

    @GetMapping(path = "/confirm-account")
    String confirmAccount(@PathVariable String token);

    @GetMapping(path = "/reset-pass")
    String resetPass(@PathVariable String token);

}
