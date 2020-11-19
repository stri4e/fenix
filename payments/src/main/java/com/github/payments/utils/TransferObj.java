package com.github.payments.utils;

import com.github.currencies.rates.payload.RatePayload;
import com.github.currencies.rates.payload.RatesPayload;
import com.github.payments.dto.*;
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
                data.getTransfers()
        );
    }

    public static BillDto fromBill(Bill data) {
        return new BillDto(
                data.getId(),
                data.getAmount(),
                data.getAmountPaid(),
                data.getAsset().getName(),
                data.getAddress(),
                data.getTransfers(),
                data.getPaymentType().getAlias(),
                fromWho(data.getWho()),
                fromWhom(data.getWhom())
        );
    }

    public static Asset toAsset(AssetDto data) {
        return new Asset(
                data.getId(),
                data.getOwner(),
                data.getName(),
                data.getFullName(),
                data.getPow(),
                data.getAssetType()
        );
    }

    public static AssetDto fromAsset(Asset data) {
        return new AssetDto(
                data.getId(),
                data.getOwner(),
                data.getName(),
                data.getFullName(),
                data.getPow(),
                data.getAssetType()
        );
    }

    public static CurrentRateDto fromCurrentRate(CurrentRate data) {
        return new CurrentRateDto(
                data.getId(),
                data.getAssetName(),
                data.getRates().stream()
                        .map(TransferObj::toRate)
                        .collect(Collectors.toList())
        );
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

    public static RateDto toRate(Rate data) {
        return new RateDto(
                data.getId(),
                data.getTime(),
                data.getAssetNameQuote(),
                data.getRate()
        );
    }

    public static PaymentTypes toPaymentType(PaymentTypesDto data) {
        return new PaymentTypes(
                data.getId(),
                data.getAlias()
        );
    }

    public static PaymentTypesDto fromPaymentType(PaymentTypes data) {
        return new PaymentTypesDto(
                data.getId(),
                data.getAlias()
        );
    }

    public static Accountant toAccountant(AccountantDto data) {
        return new Accountant(
                data.getFirsName(),
                data.getLastName(),
                data.getPatronymic()
        );
    }

    public static AccountantDto fromAccountant(Accountant data) {
        return new AccountantDto(
                data.getId(),
                data.getFirsName(),
                data.getLastName(),
                data.getPatronymic()
        );
    }

    public static Who toWho(WhoDto data) {
        return new Who(
                data.getId(),
                data.getFirsName(),
                data.getLastName(),
                data.getPatronymic()
        );
    }

    public static WhoDto fromWho(Who data) {
        return new WhoDto(
                data.getId(),
                data.getFirsName(),
                data.getLastName(),
                data.getPatronymic()
        );
    }

    public static Whom toWhom(WhomDto data) {
        return new Whom(
                data.getId(),
                data.getFirsName(),
                data.getLastName(),
                data.getPatronymic()
        );
    }

    public static WhomDto fromWhom(Whom data) {
        return new WhomDto(
                data.getId(),
                data.getFirsName(),
                data.getLastName(),
                data.getPatronymic()
        );
    }

}
