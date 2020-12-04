package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IConfirmAccount;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.User;
import com.github.users.center.entity.UserAlias;
import com.github.users.center.payload.RenderTemplate;
import com.github.users.center.services.IConfirmService;
import com.github.users.center.services.IUserAliasService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.lang.Boolean.TRUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/confirm-account")
public class ConfirmAccount implements IConfirmAccount {

    private final IConfirmService confirmService;

    private final IUserService userService;

    private final IUserAliasService userAliasService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public RenderTemplate confirmAccount(String token) {
        ConfirmToken ct = this.confirmService.readByToken(token);
        User user = ct.getUser();
        this.userService.updateIsEnable(TRUE, user.getId());
        var alias = UUID.randomUUID().toString();
        this.userAliasService.save(new UserAlias(user, alias));
        return RenderTemplate.success();
    }

}
