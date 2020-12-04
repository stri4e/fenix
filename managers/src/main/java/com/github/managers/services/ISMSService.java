package com.github.managers.services;

import com.github.managers.payload.SingleMessage;
import com.github.managers.services.impl.FiltersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        name = "messenger",
        fallback = FiltersService.class,
        contextId = "messengerId"
)
public interface ISMSService {

    @PostMapping(
            path = "/single/edit"
    )
    void singleSMS(@Valid @RequestBody SingleMessage payload);

}
