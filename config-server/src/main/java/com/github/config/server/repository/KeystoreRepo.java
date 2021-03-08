package com.github.config.server.repository;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Keystore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeystoreRepo extends JpaRepository<Keystore, Long> {

    Optional<Keystore> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Keystore ks set ks.status=:status where ks.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
