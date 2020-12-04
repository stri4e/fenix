package com.github.messenger.controllers;

import infobip.api.model.account.AccountBalance;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

public interface IInfobipAccountController {

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AccountBalance findBalance();

}
