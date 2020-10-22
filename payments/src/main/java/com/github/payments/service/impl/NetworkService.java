package com.github.payments.service.impl;

import com.github.currencies.rates.controllers.IExchangeController;
import com.github.currencies.rates.payload.RatesPayload;
import com.github.payments.entity.Asset;
import com.github.payments.entity.AssetType;
import com.github.payments.entity.CurrentRate;
import com.github.payments.service.IAssetsService;
import com.github.payments.service.ICurrentRateService;
import com.github.payments.service.INetworkService;
import com.github.payments.service.IRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.payments.entity.AssetType.crypto;
import static com.github.payments.entity.AssetType.fiat;
import static com.github.payments.utils.TransferObj.toCurrentRate;

@Service
@RequiredArgsConstructor
public class NetworkService implements INetworkService {

    @Value(value = "${exchange.key}")
    private String exchangeKey;

    private final IExchangeController exchangeController;

    private final IAssetsService assetsService;

    private final ICurrentRateService currentRateService;

    @Override
    public Optional<RatesPayload> fetchRates(String currencyName) {
        return this.exchangeController
                .findRates(this.exchangeKey, currencyName, Boolean.FALSE);
    }

    @Override
    public void rates() {
        List<Asset> fiats = this.assetsService.readAssetsByType(fiat);
        List<Asset> cryptos = this.assetsService.readAssetsByType(crypto);
        List<String> all = cryptos.stream()
                .map(Asset::getName)
                .collect(Collectors.toList());
        List<RatesPayload> payloads = all.stream()
                .map(this::fetchRates)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        List<CurrentRate> rates = payloads.stream()
                .map(r -> toCurrentRate(
                        r, fiats.stream()
                                .map(Asset::getName)
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());
        this.currentRateService.saveAll(rates);
    }

}
