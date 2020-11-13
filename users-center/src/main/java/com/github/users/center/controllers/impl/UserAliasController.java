package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IUserAliasController;
import com.github.users.center.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/aliases")
public class UserAliasController implements IUserAliasController {

    private final INotificationService notificationService;

    @Override
    public String aliasForPush(UUID userId) {
        return this.notificationService.ending(userId);
    }

    @Override
    public String aliasForListening(UUID userId) {
        return this.notificationService.ending(userId);
    }

}
