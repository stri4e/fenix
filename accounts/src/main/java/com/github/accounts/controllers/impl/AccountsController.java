package com.github.accounts.controllers.impl;

import com.github.accounts.controllers.IAccountsController;
import com.github.accounts.dto.AccountDto;
import com.github.accounts.dto.CustomerDto;
import com.github.accounts.dto.ProductDto;
import com.github.accounts.entity.*;
import com.github.accounts.services.*;
import com.github.accounts.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.accounts.utils.TransferObj.*;
import static java.util.concurrent.CompletableFuture.runAsync;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class AccountsController implements IAccountsController {

    private final IAccountsService accountsService;

    private final IProductService productService;

    private final IProfilesService profilesService;

    private final IAddressService addressService;

    private final IContactsService contactsService;

    private final ICustomerService customerService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountDto findAccount(UUID userId) {
        List<ProductDto> products = List.of();
        Account account = this.accountsService.readByUserId(userId);
        if (!account.isViewsEmpty()) {
            Set<View> views = account.getViews();
            products = this.productService.readByIds(
                    views.stream()
                            .limit(30)
                            .map(View::getProductId)
                            .collect(Collectors.toList())
            ).orElse(new ArrayList<>());
        }
        return fromAccount(account, products);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountDto findByParams(String email, String phone) {
        return fromAccount(this.accountsService.readByEmailAndPhone(email, phone));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountDto save(UUID userId, AccountDto payload) {
        return fromAccount(this.accountsService.create(
                new Account(
                        userId,
                        this.profilesService.create(toProfile(payload.getProfile())),
                        this.contactsService.create(toContact(payload.getContact())),
                        this.addressService.create(toAddress(payload.getAddress()))
                )
        )).and(() ->
                this.customerService.save(
                        userId,
                        new CustomerDto(
                                payload.getProfile(),
                                payload.getContact(),
                                payload.getAddress()
                        )
                )
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AccountDto saveDefault(UUID userId) {
        Profile profileDef = this.profilesService.create(Profile.profileDef());
        Contact contactDef = this.contactsService.create(Contact.contactDef());
        Address addressDef = this.addressService.create(Address.addressDef());
        Account account = new Account(userId, profileDef, contactDef, addressDef);
        return fromAccount(this.accountsService.create(account));
    }

}
