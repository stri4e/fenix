package com.github.payments.service;

import com.github.payments.service.impl.UsersNotifyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(
        name = "users-center",
        fallback = UsersNotifyService.class,
        contextId = "usersCenterId"
)
public interface IUsersNotifyService {

    @GetMapping(path = "/v1/notification/push/ending")
    Optional<String> findEndingUrl(Long userId);

}
