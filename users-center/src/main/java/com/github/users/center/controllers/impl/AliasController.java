package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IAliasController;
import com.github.users.center.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/aliases")
public class AliasController implements IAliasController {

    private final INotificationService notificationService;

    @Override
    public String aliasForPush(Long userId) {
        return this.notificationService.ending(userId);
    }

    @Override
    public String aliasForListening(Long userId) {
        return this.notificationService.ending(userId);
    }
}
