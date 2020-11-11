package com.github.users.center.services;

import com.github.users.center.entity.NotificationPrefix;

public interface INotificationService {

    void save(NotificationPrefix notificationPrefix);

    String ending(Long userId);

}
