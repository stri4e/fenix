package com.github.employees.controllers;

import com.github.employees.payload.AddressDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface IAddressController {

    @PostMapping
    Mono<AddressDto> save(@PathVariable(name = "accountId") String accountId,
                          @RequestBody AddressDto payload);

    @PutMapping
    Mono<Void> update(@PathVariable(name = "accountId") String accountId,
                      @RequestBody AddressDto payload);

}
