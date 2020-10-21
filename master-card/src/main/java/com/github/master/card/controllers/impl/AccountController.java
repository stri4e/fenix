package com.github.master.card.controllers.impl;

import com.github.master.card.controllers.IAccountController;
import com.github.master.card.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/account")
public class AccountController implements IAccountController {

    private final IAccountService accountService;

    @Override
    public String findActive() {
        return this.accountService.readActive().getId().toString();
    }

}
