package com.github.payments.service;

import com.github.payments.entity.Asset;
import com.github.payments.entity.AssetType;

import java.util.List;

public interface IAssetsService {

    List<Asset> readAssetsByType(AssetType type);

}
