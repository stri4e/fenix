package com.github.deliveries.repository;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.NovaposhtaSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovaposhtaSettingsRepo extends JpaRepository<NovaposhtaSettings, Long> {

    Optional<NovaposhtaSettings> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update NovaposhtaSettings ns set ns.baseUrl=:baseUrl, ns.apiKey=:apiKey where ns.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "baseUrl") String baseUrl,
            @Param(value = "apiKey") String apiKey
    );

    @Modifying
    @Query(value = "update NovaposhtaSettings ns set ns.status=:status where ns.id=:id")
    void delete(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
