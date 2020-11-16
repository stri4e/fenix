package com.github.emails.services;

import com.github.emails.payload.RenderTemplate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(
        name = "users-center",
        fallback = UsersCenterService.class,
        contextId = "usersCenterId"
)
public interface IUsersCenterService {

    @PostMapping(path = "/v1/confirm-account/edit")
    Optional<RenderTemplate> confirmAccount(String token);

    @PostMapping(path = "/v1/reset-pass/edit")
    Optional<RenderTemplate> resetPassword(String token);

}
