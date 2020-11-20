package com.github.users.center.services;

import com.github.users.center.dto.AccountDto;
import com.github.users.center.services.impl.AccountsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(
        name = "accounts",
        fallback = AccountsService.class
)
public interface IAccountsService {

    @PostMapping(
            path = "/v1/edit/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void createAccount(@PathVariable(name = "userId") UUID userId,
                       @RequestBody AccountDto payload);

}
