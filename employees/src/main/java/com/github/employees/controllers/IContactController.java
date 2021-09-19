package com.github.employees.controllers;

import com.github.employees.payload.AddressDto;
import com.github.employees.payload.ContactDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface IContactController {

    @PostMapping
    Mono<ContactDto> save(@PathVariable(name = "accountId") String accountId,
                          @RequestBody ContactDto payload);

    @PutMapping
    Mono<Void> update(@PathVariable(name = "accountId") String accountId,
                      @RequestBody ContactDto payload);

}
