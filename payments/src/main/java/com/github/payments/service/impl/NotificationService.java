package com.github.payments.service.impl;

import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.Notification;
import com.github.payments.exceptions.NotFound;
import com.github.payments.repository.NotificationRepo;
import com.github.payments.service.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationRepo notificationRepo;

    @Override
    public Notification findByBillId(Long billId) {
        return this.notificationRepo.findByBill_Id(billId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Notification create(Notification notification) {
        return this.notificationRepo.save(notification);
    }

    @Override
    public void updateStatus(Long id) {
        this.notificationRepo.updateStatus(id, EntityStatus.off);
    }
}
