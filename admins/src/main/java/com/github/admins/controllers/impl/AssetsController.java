package com.github.admins.controllers.impl;

import com.github.admins.controllers.IAssetsController;
import com.github.admins.dto.AssetDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IAssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payments/assets")
public class AssetsController implements IAssetsController {

    private final IAssetsService assetsService;

    @Override
    public List<AssetDto> findAssetsByStatus(EntityStatus status) {
        return this.assetsService.findAssetsByStatus(status)
                .orElseThrow(NotFound::new);
    }

    @Override
    public AssetDto findAsset(Long id) {
        return this.assetsService.findAsset(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public AssetDto save(AssetDto payload) {
        return this.assetsService.save(payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    public void update(AssetDto payload) {
        this.assetsService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.assetsService.remove(id);
    }
}
