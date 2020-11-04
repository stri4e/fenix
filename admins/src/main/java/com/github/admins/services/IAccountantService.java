package com.github.admins.services;

import com.github.admins.dto.AccountantDto;
import com.github.admins.services.impl.AccountantService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "payments",
        fallback = AccountantService.class,
        contextId = "accountantId"
)
public interface IAccountantService {

    @PostMapping(
            path = "/v1/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<AccountantDto> save(@RequestBody AccountantDto payload);

    @PutMapping(
            path = "/v1/edit"
    )
    void update(@RequestBody AccountantDto payload);

    @DeleteMapping(
            path = "/v1/edit/{id}"
    )
    void remove(@PathVariable Long id);

}
