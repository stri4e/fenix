package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IAccountController;
import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.services.IAccountService;
import com.github.ethereum.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/accounts")
public class AccountController implements IAccountController {

    private final IAccountService accountService;

    @Override
    public Page<AccountDto> findByStatus(EntityStatus status, Pageable pageable) {
        Page<Account> accounts = this.accountService.readAllByStatus(status);
        return new PageImpl<>(
                accounts.stream()
                        .map(TransferObj::fromAccount)
                        .collect(Collectors.toList()),
                pageable, accounts.getTotalElements()
        );
    }
}
