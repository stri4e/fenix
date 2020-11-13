package com.github.users.center.repository;

import com.github.users.center.entity.NotificationPrefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepo extends JpaRepository<NotificationPrefix, Long> {

    Optional<NotificationPrefix> findByUser_Id(Long userId);

    @Query(value = "select n.ending from NotificationPrefix n where n.user.id=:userId")
    String findEnding(@Param(value = "userId") UUID userId);

}
