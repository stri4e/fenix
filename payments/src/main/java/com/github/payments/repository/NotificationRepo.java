package com.github.payments.repository;

import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    Optional<Notification> findByBill_Id(Long billId);

    @Modifying
    @Query(value = "update Notification n set n.status=:status where n.id=:id")
    void updateStatus(@Param(value = "id") Long id,
                      @Param(value = "status") EntityStatus status);

}
