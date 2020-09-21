package com.github.admins.controllers.impl;

import com.github.admins.controllers.IUsersController;
import com.github.admins.dto.UserRegDto;
import com.github.admins.services.IUsersCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersController implements IUsersController {

    private final IUsersCenterService usersCenterService;

    @Override
    public void adminsReg(@Valid UserRegDto payload) {
        this.usersCenterService.createAdmins(payload);
    }

    @Override
    public void managersReg(@Valid UserRegDto payload) {
        this.usersCenterService.createManager(payload);
    }

    @Override
    public void updateManagersIsLocked(String email, Boolean isLocked) {
        this.usersCenterService.updateManagersIsLocked(email, isLocked);
    }

    @Override
    public void updateAdminsIsLocked(String email, Boolean isLocked) {
        this.usersCenterService.updateAdminsIsLocked(email, isLocked);
    }

}
