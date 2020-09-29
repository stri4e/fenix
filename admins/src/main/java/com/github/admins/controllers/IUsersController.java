package com.github.admins.controllers;

import com.github.admins.dto.LockedDto;
import com.github.admins.dto.UserRegDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IUsersController {

    @PostMapping(path = "/admins/reg")
    @ResponseStatus(value = HttpStatus.CREATED)
    void adminsReg(@Valid @RequestBody UserRegDto payload);

    @PostMapping(path = "/managers/reg")
    @ResponseStatus(value = HttpStatus.CREATED)
    void managersReg(@Valid @RequestBody UserRegDto payload);

    @PutMapping(path = "/managers")
    void updateManagersIsLocked(@RequestBody LockedDto payload);

    @PutMapping(path = "/admins")
    void updateAdminsIsLocked(@RequestBody LockedDto payload);

}
