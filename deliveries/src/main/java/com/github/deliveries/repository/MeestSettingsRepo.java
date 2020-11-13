package com.github.deliveries.repository;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.MeestSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeestSettingsRepo extends JpaRepository<MeestSettings, Long> {

    Optional<MeestSettings> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update MeestSettings ms set ms.status=:status where ms.id=:id")
    void delete(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
