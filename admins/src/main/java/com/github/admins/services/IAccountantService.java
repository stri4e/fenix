package com.github.admins.services;

import com.github.admins.dto.AccountantDto;
import com.github.admins.services.impl.AccountantService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    Optional<AccountantDto> save(AccountantDto payload);

    @PutMapping(
            path = "/v1/edit"
    )
    void update(AccountantDto payload);

    @DeleteMapping(
            path = "/v1/edit"
    )
    void remove(Long id);

}
