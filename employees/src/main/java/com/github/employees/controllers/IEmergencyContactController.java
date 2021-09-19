package com.github.employees.controllers;

import com.github.employees.payload.AddressDto;
import com.github.employees.payload.EmergencyContactDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface IEmergencyContactController {

    @PostMapping
    Mono<EmergencyContactDto> save(@PathVariable(name = "accountId") String accountId,
                          @RequestBody EmergencyContactDto payload);

    @PutMapping
    Mono<Void> update(@PathVariable(name = "accountId") String accountId,
                      @RequestBody EmergencyContactDto payload);

}
