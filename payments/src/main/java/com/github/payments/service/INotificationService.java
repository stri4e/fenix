package com.github.payments.service;

import com.github.payments.entity.Notification;

public interface INotificationService {

    Notification findByBillId(Long billId);

    Notification create(Notification notification);

    void updateStatus(Long id);

}
