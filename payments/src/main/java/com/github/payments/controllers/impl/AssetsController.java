package com.github.payments.controllers.impl;

import com.github.payments.controllers.IAssetsController;
import com.github.payments.dto.AssetDto;
import com.github.payments.entity.EntityStatus;
import com.github.payments.service.IAssetsService;
import com.github.payments.utils.Logging;
import com.github.payments.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.payments.utils.TransferObj.fromAsset;
import static com.github.payments.utils.TransferObj.toAsset;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/assets")
public class AssetsController implements IAssetsController {

    private final IAssetsService assetsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<AssetDto> findAssets() {
        return this.assetsService.readByStatus(EntityStatus.on)
                .stream()
                .map(TransferObj::fromAsset)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<AssetDto> findAssetsByStatus(EntityStatus status) {
        return this.assetsService.readByStatus(status)
                .stream()
                .map(TransferObj::fromAsset)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AssetDto findAsset(Long id) {
        return fromAsset(this.assetsService.readById(id));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AssetDto save(AssetDto payload) {
        return fromAsset(this.assetsService.create(toAsset(payload)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(AssetDto payload) {
        this.assetsService.update(
                payload.getId(), payload.getOwner(),
                payload.getName(), payload.getFullName(),
                payload.getPow(), payload.getAssetType()
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.assetsService.remove(id);
    }
}
