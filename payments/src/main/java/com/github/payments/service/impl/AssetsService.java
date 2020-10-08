package com.github.payments.service.impl;

import com.github.payments.entity.Asset;
import com.github.payments.entity.AssetType;
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
        return null;
    }
}
