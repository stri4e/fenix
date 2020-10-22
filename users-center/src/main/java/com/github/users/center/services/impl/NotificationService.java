package com.github.users.center.services.impl;

import com.github.users.center.entity.Notification;
import com.github.users.center.repository.NotificationRepo;
import com.github.users.center.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationRepo notificationRepo;

    @Override
    public void save(Notification notification) {
        this.notificationRepo.save(notification);
    }

    @Override
    public String ending(Long userId) {
        return this.notificationRepo.findEnding(userId);
    }
}
