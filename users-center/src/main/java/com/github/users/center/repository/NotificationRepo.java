package com.github.users.center.repository;

import com.github.users.center.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUser_Id(Long userId);

    @Query(value = "select n.ending from Notification n where n.user.id=:userId")
    String findEnding(@Param(value = "userId") Long userId);

}
