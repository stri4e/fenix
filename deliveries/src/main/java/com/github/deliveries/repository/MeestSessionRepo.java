package com.github.deliveries.repository;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.MeestSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MeestSessionRepo extends JpaRepository<MeestSession, Long> {

    Optional<MeestSession> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update MeestSession mat set mat.token=:token, mat.refreshToken=:refreshToken, mat.expireIn=:expireIn where mat.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "token") String token,
            @Param(value = "refreshToken") String refreshToken,
            @Param(value = "expireIn") Date expireIn
    );

    @Modifying
    @Query(value = "update MeestSession mat set mat.status=:status where mat.id=:id")
    void delete(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
