package com.github.users.center.services;

import com.github.users.center.entity.NotificationPrefix;

import java.util.UUID;

public interface INotificationService {

    void save(NotificationPrefix notificationPrefix);

    String ending(UUID userId);

}
