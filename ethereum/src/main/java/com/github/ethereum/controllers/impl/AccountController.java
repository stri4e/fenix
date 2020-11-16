package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IAccountController;
import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.exceptions.Conflict;
import com.github.ethereum.services.IAccountService;
import com.github.ethereum.utils.Logging;
import com.github.ethereum.utils.TransferObj;
import com.github.facade.ethrereum.IFacadeEthereum;
import com.github.facade.ethrereum.model.KeyPair;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.ethereum.utils.TransferObj.fromAccount;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/accounts")
public class AccountController implements IAccountController {

    private final IFacadeEthereum facadeEthereum;

    private final IAccountService accountService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<AccountDto> findByStatus(EntityStatus status, Pageable pageable) {
        Page<Account> accounts = this.accountService.readAllByStatus(pageable, status);
        return new PageImpl<>(
                accounts.stream()
                        .map(TransferObj::fromAccount)
                        .collect(Collectors.toList()),
                pageable, accounts.getTotalElements()
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountDto save(UUID userId) {
        int count = this.accountService.countAccountByUserId(userId);
        if (count < BigInteger.TEN.intValue()) {
            KeyPair keys = this.facadeEthereum.generateKeys();
            Account tmp = new Account(
                    userId, keys.getPrivateKey(),
                    keys.getPublicKey(), keys.getAddress(),
                    BigInteger.ZERO, EntityStatus.on
            );
            return fromAccount(this.accountService.create(tmp));
        }
        throw new Conflict("Max limit of accounts.");
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public String findAvailableAddress(UUID userId) {
        return this.accountService
                .readByUserIdAndByStatus(userId, EntityStatus.off)
                .getAddress();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void activateAddress(String address) {
        this.accountService.updateStatus(address, EntityStatus.on);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(String address) {
        this.accountService.updateStatus(address, EntityStatus.off);
    }

}
