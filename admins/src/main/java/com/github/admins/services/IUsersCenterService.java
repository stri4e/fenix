package com.github.admins.services;

import com.github.admins.dto.ForgotPassDto;
import com.github.admins.dto.LockedDto;
import com.github.admins.dto.UserRegDto;
import com.github.admins.services.impl.UsersCenterService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@FeignClient(
        name = "users-center",
        fallback = UsersCenterService.class,
        contextId = "usersCenterId"
)
public interface IUsersCenterService {

    @PostMapping(
            path = "/v1/admins/edit/reg",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void createAdmins(@RequestBody UserRegDto payload);

    @PostMapping(
            path = "/v1/managers/edit/reg",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void createManager(@RequestBody UserRegDto payload);

    @PutMapping(
            path = "/v1/locked/edit"
    )
    void updateIsLocked(@RequestBody LockedDto payload);

    @PostMapping(path = "/v1/forgot-pass/edit")
    @ResponseStatus(code = HttpStatus.OK)
    void staffForgotPass(
            @Valid @RequestBody ForgotPassDto payload
    );

}
