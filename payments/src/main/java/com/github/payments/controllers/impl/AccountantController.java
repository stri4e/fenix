package com.github.payments.controllers.impl;

import com.github.payments.controllers.IAccountantController;
import com.github.payments.dto.AccountantDto;
import com.github.payments.service.IAccountantService;
import com.github.payments.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountantDto save(AccountantDto payload) {
        return fromAccountant(this.accountantService.create(toAccountant(payload)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountantDto findActive() {
        return fromAccountant(this.accountantService.readActive());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(AccountantDto payload) {
        this.accountantService.update(
                payload.getId(), payload.getFirsName(),
                payload.getLastName(), payload.getPatronymic()
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.accountantService.delete(id);
    }
}
