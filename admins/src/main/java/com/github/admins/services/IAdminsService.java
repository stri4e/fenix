package com.github.admins.services;

import com.github.admins.dto.UserRegDto;
import com.github.admins.services.impl.AdminsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "users-center",
        fallback = AdminsService.class,
        contextId = "adminsId"
)
public interface IAdminsService {

    @PostMapping(
            path = "/v1/admins/reg",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void create(@RequestBody UserRegDto payload);

}
