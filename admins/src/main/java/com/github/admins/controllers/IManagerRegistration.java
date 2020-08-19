package com.github.admins.controllers;

import com.github.admins.dto.UserRegDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface IManagerRegistration {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    void managersReg(@Valid @RequestBody UserRegDto payload);

}
