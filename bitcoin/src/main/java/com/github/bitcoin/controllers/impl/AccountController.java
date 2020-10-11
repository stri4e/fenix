package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.IAccountController;
import com.github.bitcoin.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/accounts")
public class AccountController implements IAccountController {

    private final IAccountService accountService;

}
