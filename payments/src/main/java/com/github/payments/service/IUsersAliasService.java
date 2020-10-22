package com.github.payments.service;

import com.github.payments.service.impl.UsersAliasService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(
        name = "users-center",
        fallback = UsersAliasService.class,
        contextId = "usersCenterId"
)
public interface IUsersAliasService {

    @GetMapping(path = "/v1/aliases/push")
    Optional<String> findEndingUrl(Long userId);

}
