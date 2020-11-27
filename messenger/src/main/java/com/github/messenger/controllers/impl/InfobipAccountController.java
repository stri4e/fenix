package com.github.messenger.controllers.impl;

import com.github.messenger.controllers.IInfobipAccountController;
import com.github.messenger.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import infobip.api.client.GetAccountBalance;
import infobip.api.model.account.AccountBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/infobit/account")
public class InfobipAccountController implements IInfobipAccountController {

    private final GetAccountBalance getAccountBalance;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountBalance findBalance() {
        return this.getAccountBalance.execute();
    }

}
