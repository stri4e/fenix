package com.github.payments.service;

import com.github.payments.entity.Asset;
import com.github.payments.entity.AssetType;
import com.github.payments.entity.EntityStatus;

import java.util.List;

public interface IAssetsService {

    List<Asset> readAssetsByType(AssetType type);

    Asset readByName(String name);

    List<Asset> readByStatus(EntityStatus status);

    Asset readById(Long id);

    Asset create(Asset asset);

    void update(
            Long id, String owner,
            String name, String fullName,
            Integer pow, AssetType assetType
    );

    void remove(Long id);

}
