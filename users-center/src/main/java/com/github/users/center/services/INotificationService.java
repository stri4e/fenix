package com.github.users.center.services;

import com.github.users.center.entity.Notification;

public interface INotificationService {

    void save(Notification notification);

    String ending(Long userId);

}
