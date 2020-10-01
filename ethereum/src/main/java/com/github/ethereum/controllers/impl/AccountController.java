package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IAccountController;
import com.github.ethereum.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements IAccountController {

    private final IAccountService accountService;

}
