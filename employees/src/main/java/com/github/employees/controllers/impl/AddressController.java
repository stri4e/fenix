package com.github.employees.controllers.impl;

import com.github.employees.controllers.IAddressController;
import com.github.employees.payload.AddressDto;
import com.github.employees.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/addresses")
public class AddressController implements IAddressController {

    private final IAccountService accountService;

    @Override
    public Mono<AddressDto> save(String accountId, AddressDto payload) {
        return null;
    }

    @Override
    public Mono<Void> update(String accountId, AddressDto payload) {
        return null;
    }
}
