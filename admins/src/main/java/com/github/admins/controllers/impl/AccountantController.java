package com.github.admins.controllers.impl;

import com.github.admins.controllers.IAccountantController;
import com.github.admins.dto.AccountantDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.services.IAccountantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payments/accountant")
public class AccountantController implements IAccountantController {

    private final IAccountantService accountantService;

    @Override
    public AccountantDto save(AccountantDto payload) {
        return this.accountantService.save(payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    public void update(AccountantDto payload) {
        this.accountantService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.accountantService.remove(id);
    }
}
