package com.github.employees.controllers.impl;

import com.github.employees.controllers.IAccountController;
import com.github.employees.entities.EntityStatus;
import com.github.employees.payload.AccountDto;
import com.github.employees.services.IAccountService;
import com.github.employees.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.github.employees.utils.TransferObj.toAccount;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/accounts")
public class AccountController implements IAccountController {

    private final IAccountService accountService;

    @Override
    public Mono<AccountDto> findById(UUID employeeId) {
        return this.accountService.readByEmployeeId(employeeId)
                .map(TransferObj::fromAccount);
    }

    @Override
    public Mono<AccountDto> save(UUID employeeId, AccountDto payload) {
        return this.accountService.create(toAccount(payload).employeeId(employeeId))
                .map(TransferObj::fromAccount);
    }

    @Override
    public Mono<Void> updateStatus(UUID employeeId, EntityStatus status) {
        return this.accountService.readByEmployeeId(employeeId)
                .flatMap(account -> this.accountService.update(account.status(status)));
    }
}
