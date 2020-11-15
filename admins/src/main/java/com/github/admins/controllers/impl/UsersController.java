package com.github.admins.controllers.impl;

import com.github.admins.controllers.IUsersController;
import com.github.admins.dto.LockedDto;
import com.github.admins.dto.UserRegDto;
import com.github.admins.services.IUsersCenterService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void adminsReg(@Valid UserRegDto payload) {
        this.usersCenterService.createAdmins(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void managersReg(@Valid UserRegDto payload) {
        this.usersCenterService.createManager(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateIsLocked(LockedDto payload) {
        this.usersCenterService.updateIsLocked(payload);
    }

}
