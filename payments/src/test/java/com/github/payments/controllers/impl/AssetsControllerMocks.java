package com.github.payments.controllers.impl;

import com.github.payments.dto.AssetDto;
import com.github.payments.entity.Asset;
import com.github.payments.entity.AssetType;

import java.util.List;

public class AssetsControllerMocks {

    public static AssetDto request() {
        return new AssetDto(
                null,
                "Main",
                "eth",
                "Ethereum",
                18,
                AssetType.crypto
        );
    }

    public static AssetDto response() {
        return new AssetDto(
                1L,
                "Main",
                "eth",
                "Ethereum",
                18,
                AssetType.crypto
        );
    }

    public static AssetDto requestForUpdate() {
        return new AssetDto(
                1L,
                "Main1",
                "eth1",
                "Ethereum1",
                18,
                AssetType.crypto
        );
    }

    public static AssetDto one() {
        return new AssetDto(
                1L,
                "Ethereum",
                "eth",
                "Ethereum",
                18,
                AssetType.crypto
        );
    }

    public static AssetDto two() {
        return new AssetDto(
                2L,
                "Bitcoin",
                "btc",
                "Bitcoin",
                8,
                AssetType.crypto
        );
    }

    public static AssetDto three() {
        return new AssetDto(
                3L,
                "USA",
                "usd",
                "United States Dollar",
                8,
                AssetType.fiat
        );
    }

    public static AssetDto four() {
        return new AssetDto(
                4L,
                "Europe",
                "eur",
                "euro",
                8,
                AssetType.fiat
        );
    }

    public static AssetDto five() {
        return new AssetDto(
                5L,
                "Ukraine",
                "uah",
                "ukraine hryvnia",
                8,
                AssetType.fiat
        );
    }

    public static List<AssetDto> ASSETS = List.of(
            one(), two(), three(), four(), five()
    );

    public static Asset forSave() {
        return new Asset(
                null,
                "Main",
                "eth",
                "Ethereum",
                18,
                AssetType.crypto
        );
    }

    public static Asset oneForSave() {
        return new Asset(
                null,
                "Ethereum",
                "eth",
                "Ethereum",
                18,
                AssetType.crypto
        );
    }

    public static Asset twoForSave() {
        return new Asset(
                null,
                "Bitcoin",
                "btc",
                "Bitcoin",
                8,
                AssetType.crypto
        );
    }

    public static Asset threeForSave() {
        return new Asset(
                null,
                "USA",
                "usd",
                "United States Dollar",
                8,
                AssetType.fiat
        );
    }

    public static Asset fourForSave() {
        return new Asset(
                null,
                "Europe",
                "eur",
                "euro",
                8,
                AssetType.fiat
        );
    }

    public static Asset fiveForSave() {
        return new Asset(
                null,
                "Ukraine",
                "uah",
                "ukraine hryvnia",
                8,
                AssetType.fiat
        );
    }

    public static List<Asset> ASSETS_FOR_SAVE = List.of(
            oneForSave(), twoForSave(), threeForSave(), fourForSave(), fiveForSave()
    );

}
