package com.github.admins.controllers.impl;

import com.github.admins.controllers.IManagerRegistration;
import com.github.admins.dto.UserRegDto;
import com.github.admins.services.IManagersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/managers/reg")
@RequiredArgsConstructor
public class ManagerRegistration implements IManagerRegistration {

    private final IManagersService managersService;

    @Override
    public void managersReg(@Valid UserRegDto payload) {
        this.managersService.create(payload);
    }

}
