package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.INotificationController;
import com.github.users.center.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/notification")
public class NotificationController implements INotificationController {

    private final INotificationService notificationService;

    @Override
    public String endingUrlForPush(Long userId) {
        return this.notificationService.ending(userId);
    }

    @Override
    public String endingUrlForListening(Long userId) {
        return this.notificationService.ending(userId);
    }
}
