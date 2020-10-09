package com.github.payments.service.impl;

import com.github.payments.entity.Asset;
import com.github.payments.entity.AssetType;
import com.github.payments.entity.EntityStatus;
import com.github.payments.exceptions.NotFound;
import com.github.payments.repository.AssetsRepo;
import com.github.payments.service.IAssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AssetsService implements IAssetsService {

    private final AssetsRepo assetsRepo;

    @Override
    public List<Asset> readAssetsByType(AssetType type) {
        return this.assetsRepo.findAllByAssetType(type);
    }

    @Override
    public Asset readByName(String name) {
        return this.assetsRepo.findByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<Asset> readByStatus(EntityStatus status) {
        return this.assetsRepo.findAllByStatus(status);
    }

    @Override
    public Asset readById(Long id) {
        return this.assetsRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Asset create(Asset asset) {
        return this.assetsRepo.save(asset);
    }

    @Override
    public void update(Asset asset) {
        this.assetsRepo.save(asset);
    }

    @Override
    public void remove(Long id) {
        this.assetsRepo.update(id, EntityStatus.off);
    }
}
