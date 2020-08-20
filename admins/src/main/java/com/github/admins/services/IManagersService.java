package com.github.admins.services;

import com.github.admins.dto.UserRegDto;
import com.github.admins.payload.Product;
import com.github.admins.services.impl.ManagersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "users-center",
        fallback = ManagersService.class,
        contextId = "managersId"
)
public interface IManagersService {

    @PostMapping(
            path = "/v1/managers/reg",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void create(@RequestBody UserRegDto payload);

}
