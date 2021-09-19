package com.github.employees.controllers.impl;

import com.github.employees.controllers.IContactController;
import com.github.employees.payload.ContactDto;
import com.github.employees.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/contacts")
public class ContactController implements IContactController {

    private final IAccountService accountService;

    @Override
    public Mono<ContactDto> save(String accountId, ContactDto payload) {
        return null;
    }

    @Override
    public Mono<Void> update(String accountId, ContactDto payload) {
        return null;
    }
}
