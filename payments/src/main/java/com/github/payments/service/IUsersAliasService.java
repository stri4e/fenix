package com.github.payments.service;

import com.github.payments.service.impl.UsersAliasService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "users-center",
        fallback = UsersAliasService.class,
        contextId = "usersCenterId"
)
public interface IUsersAliasService {

    @GetMapping(path = "/v1/aliases/fetch/push/{userId}")
    Optional<String> findEndingUrl(@PathVariable(name = "userId") UUID userId);

}
