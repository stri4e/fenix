package com.github.master.card.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public interface IAccountController {

    @GetMapping(
            path = "/address"
    )
    String findActive();

}
