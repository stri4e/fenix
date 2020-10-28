package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.IAccountController;
import com.github.bitcoin.dto.AccountDto;
import com.github.bitcoin.entity.Account;
import com.github.bitcoin.entity.Address;
import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.exceptions.NotFound;
import com.github.bitcoin.services.IAccountService;
import com.github.bitcoin.services.IAddressService;
import com.github.bitcoin.utils.Logging;
import com.github.bitcoin.utils.TransferObj;
import com.github.facade.bitcoin.IFacadeBitcoin;
import com.github.facade.bitcoin.models.ChainAddress;
import com.github.facade.bitcoin.models.KeysBag;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/accounts")
public class AccountController implements IAccountController {

    private static final int ZERO = 0;

    private static final int TEN = 10;

    private final IFacadeBitcoin facadeBitcoin;

    private final IAccountService accountService;

    private final IAddressService addressService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
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
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void save(UUID userId) {
        KeysBag keys = this.facadeBitcoin.generateKeys();
        List<ChainAddress> chainAddresses =
                this.facadeBitcoin.generateAddresses(keys, ZERO, TEN);
        Account account = this.accountService.create(
                new Account(
                        keys.getMnemonic(),
                        keys.getPrivateKey(),
                        keys.getPublicKey(),
                        keys.getChainCode(),
                        keys.getTimeStamp()
                ));
        List<Address> addresses = chainAddresses.stream()
                .map(address -> new Address(
                        address.getIndex(),
                        address.getAddress(),
                        account
                )).collect(Collectors.toList());
        addresses = this.addressService.createAll(addresses);
        account.addAddresses(addresses);
        this.accountService.update(account);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public String findAvailableAddress(UUID userId) {
        Account account = this.accountService.readByUserId(userId);
        var result = account.getAddresses().stream()
                .filter(address -> EntityStatus.off.equals(address.getStatus()))
                .map(Address::getAddress)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(NotFound::new);
        this.addressService.updateStatus(result, EntityStatus.on);
        return result;
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(String address) {
        this.addressService.updateStatus(address, EntityStatus.off);
    }

}
