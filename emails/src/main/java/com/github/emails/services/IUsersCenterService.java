package com.github.emails.services;

import com.github.emails.payload.ConfirmReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(
        name = "users-center",
        fallback = UsersCenterService.class,
        contextId = "usersCenterId"
)
public interface IUsersCenterService {

    @PostMapping(path = "/v1/edit/confirm-account")
    Optional<ConfirmReport> confirmAccount(String token);

    @PostMapping(path = "/v1/edit/confirm-account")
    Optional<ConfirmReport> resetPassword(String token);

}
