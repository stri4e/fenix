package com.github.deliveries.repository;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.NovaposhtaLegalCounterparty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovaposhtaLegalCounterpartyRepo extends JpaRepository<NovaposhtaLegalCounterparty, Long> {

    Optional<NovaposhtaLegalCounterparty> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update NovaposhtaLegalCounterparty nlc set nlc.status=:status where nlc.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
