package com.github.admins.services.impl;

import com.github.admins.dto.AssetDto;
import com.github.admins.services.IAssetsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetsService implements IAssetsService {

    @Override
    public Optional<List<AssetDto>> findAssetsByStatus(String status) {
        return Optional.empty();
    }

    @Override
    public Optional<AssetDto> findAsset(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<AssetDto> save(AssetDto payload) {
        return Optional.empty();
    }

    @Override
    public void update(AssetDto payload) {

    }

    @Override
    public void remove(Long id) {

    }
}
