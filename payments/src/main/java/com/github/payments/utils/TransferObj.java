package com.github.payments.utils;

import com.github.currencies.rates.payload.RatePayload;
import com.github.currencies.rates.payload.RatesPayload;
import com.github.payments.dto.BillDto;
import com.github.payments.dto.AssetDto;
import com.github.payments.dto.CurrentRateDto;
import com.github.payments.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class TransferObj {

    public static Bill toBill(BillDto data) {
        return new Bill(
                data.getId(),
                data.getAmount(),
                data.getAmountPaid(),
                data.getAddress(),
                data.getBillType()
        );
    }

    public static BillDto fromBill(Bill data) {
        return new BillDto();
    }

    public static Asset toAsset(AssetDto data) {
        return new Asset();
    }

    public static AssetDto fromAsset(Asset data) {
        return new AssetDto();
    }

    public static Rate toRate(CurrentRateDto data) {
        return new Rate();
    }

    public static CurrentRateDto fromRate(Rate data) {
        return new CurrentRateDto();
    }

    public static CurrentRate toCurrentRate(RatesPayload data, List<String> assets) {
        return new CurrentRate(
                data.getAssetIdBase(),
                data.getRates().stream()
                        .filter(r -> assets.contains(r.getAssetIdQuote()))
                        .map(TransferObj::toRate)
                        .collect(Collectors.toList())
        );
    }

    public static Rate toRate(RatePayload data) {
        return new Rate(
                data.getTime(),
                data.getAssetIdQuote(),
                data.getRate(),
                EntityStatus.on
        );
    }

}
