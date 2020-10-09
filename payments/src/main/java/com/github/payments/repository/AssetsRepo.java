package com.github.payments.repository;

import com.github.payments.entity.Asset;
import com.github.payments.entity.AssetType;
import com.github.payments.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetsRepo extends JpaRepository<Asset, Long> {

    Optional<Asset> findByName(String name);

    List<Asset> findAllByStatus(EntityStatus status);

    List<Asset> findAllByAssetType(AssetType type);

    @Modifying
    @Query(value = "update Asset a set a.status=:status where a.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
