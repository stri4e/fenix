package com.github.admins.controllers.impl;

import com.github.admins.controllers.IUsersRegistration;
import com.github.admins.dto.UserRegDto;
import com.github.admins.services.IUsersCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersRegistration implements IUsersRegistration {

    private final IUsersCenterService adminsService;

    @Override
    public void adminsReg(@Valid UserRegDto payload) {
        this.adminsService.createAdmins(payload);
    }

    @Override
    public void managersReg(@Valid UserRegDto payload) {
        this.adminsService.createManager(payload);
    }

}
