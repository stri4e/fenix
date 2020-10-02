package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IAccountController;
import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.services.IAccountService;
import com.github.ethereum.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountController implements IAccountController {

    private final IAccountService accountService;

    @Override
    public List<AccountDto> findByStatus(EntityStatus status) {
        return this.accountService.readByStatus(status).stream()
                .map(TransferObj::fromAccount)
                .collect(Collectors.toList());
    }

}
