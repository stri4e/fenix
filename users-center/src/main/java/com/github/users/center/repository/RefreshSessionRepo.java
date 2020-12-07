package com.github.users.center.repository;

import com.github.users.center.entity.EntityStatus;
import com.github.users.center.entity.RefreshSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RefreshSessionRepo extends JpaRepository<RefreshSession, Long> {

    List<RefreshSession> findAllByUserId(UUID userId);

    RefreshSession findByUserIdAndStatus(UUID userID, EntityStatus status);

    @Modifying
    @Query(value = "update RefreshSession rs set rs.status=:status where rs.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
