package com.github.admins.controllers.impl;

import com.github.admins.controllers.IAccountantController;
import com.github.admins.dto.AccountantDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.services.IAccountantService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payments/accountant")
public class AccountantController implements IAccountantController {

    private final IAccountantService accountantService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountantDto save(AccountantDto payload) {
        return this.accountantService.save(payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(AccountantDto payload) {
        this.accountantService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.accountantService.remove(id);
    }
}
