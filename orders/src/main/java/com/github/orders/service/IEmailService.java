package com.github.orders.service;

import com.github.orders.payload.EmailNotification;
import com.github.orders.service.impl.EmailService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "emails",
        fallback = EmailService.class
)
public interface IEmailService {

    @PostMapping(
            path = "/v1/edit/registration/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void registrationOrderNotify(@RequestBody EmailNotification payload);

}
