package com.github.payments.controllers.impl;

import com.github.payments.controllers.IAccountantController;
import com.github.payments.dto.AccountantDto;
import com.github.payments.entity.Accountant;
import com.github.payments.service.IAccountantService;
import com.github.payments.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.payments.utils.TransferObj.fromAccountant;
import static com.github.payments.utils.TransferObj.toAccountant;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/accountant")
public class AccountantController implements IAccountantController {

    private final IAccountantService accountantService;

    @Override
    public AccountantDto save(AccountantDto payload) {
        return fromAccountant(this.accountantService.create(toAccountant(payload)));
    }

    @Override
    public AccountantDto findActive() {
        return fromAccountant(this.accountantService.readActive());
    }

    @Override
    public void update(AccountantDto payload) {
        Accountant accountant = this.accountantService.readActive();
        accountant.setFirsName(payload.getFirsName());
        accountant.setLastName(payload.getLastName());
        accountant.setPatronymic(payload.getPatronymic());
        this.accountantService.update(accountant);
    }

    @Override
    public void remove(Long id) {
        this.accountantService.delete(id);
    }
}
