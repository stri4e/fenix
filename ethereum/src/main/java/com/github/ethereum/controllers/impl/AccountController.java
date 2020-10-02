package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IAccountController;
import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.exceptions.NoContent;
import com.github.ethereum.services.IAccountService;
import com.github.ethereum.utils.TransferObj;
import com.github.wrapper.ethrereum.facade.IFacadeEthereum;
import com.github.wrapper.ethrereum.model.KeyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/accounts")
public class AccountController implements IAccountController {

    private final IFacadeEthereum facadeEthereum;

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

    @Override
    public String findAvailableAddress(Long userId) {
        Account account = this.accountService
                .readByUserIdAndByStatus(userId, EntityStatus.off);
        if (Objects.isNull(account)) {
            List<Account> accounts = this.accountService.readByUserId(userId);
            if (accounts.size() < BigInteger.TEN.intValue()) {
                KeyPair keys = this.facadeEthereum.generateKeys();
                Account tmp = new Account(
                        userId, keys.getPrivateKey(),
                        keys.getPublicKey(), keys.getAddress(),
                        BigInteger.ZERO, EntityStatus.on
                );
                account = this.accountService.create(tmp);
            } else {
                throw new NoContent("Max limit of accounts.");
            }
        }
        return account.getAddress();
    }

}
