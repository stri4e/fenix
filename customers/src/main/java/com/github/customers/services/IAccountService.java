package com.github.customers.services;

import com.github.customers.dto.CustomerDto;
import com.github.customers.services.impl.AccountService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.UUID;

@FeignClient(
        name = "accounts",
        fallback = AccountService.class
)
public interface IAccountService {

    @PutMapping(
            path = "/v1/edit/{userId}"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void updateByCustomer(@PathVariable UUID userId, @Valid @RequestBody CustomerDto payload);

}
