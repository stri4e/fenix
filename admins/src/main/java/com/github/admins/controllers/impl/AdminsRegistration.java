package com.github.admins.controllers.impl;

import com.github.admins.controllers.IAdminsRegistration;
import com.github.admins.dto.UserRegDto;
import com.github.admins.services.IAdminsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/admins/reg")
@RequiredArgsConstructor
public class AdminsRegistration implements IAdminsRegistration {

    private final IAdminsService adminsService;

    @Override
    public void adminsReg(@Valid UserRegDto payload) {
        this.adminsService.create(payload);
    }

}
