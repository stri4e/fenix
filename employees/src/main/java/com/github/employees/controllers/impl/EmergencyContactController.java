package com.github.employees.controllers.impl;

import com.github.employees.controllers.IEmergencyContactController;
import com.github.employees.payload.AddressDto;
import com.github.employees.payload.EmergencyContactDto;
import com.github.employees.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/emergency/contacts")
public class EmergencyContactController implements IEmergencyContactController {

    private final IAccountService accountService;

    @Override
    public Mono<EmergencyContactDto> save(String accountId, EmergencyContactDto payload) {
        return null;
    }

    @Override
    public Mono<Void> update(String accountId, EmergencyContactDto payload) {
        return null;
    }
}
