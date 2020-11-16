package com.github.admins.controllers.impl;

import com.github.admins.dto.AssetDto;
import com.github.admins.dto.AssetType;

import java.util.List;

public class AssetsControllerMocks {

    public static AssetDto request() {
        return new AssetDto(
                "Owner",
                "Vasia",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static AssetDto response() {
        return new AssetDto(
                1L,
                "Owner",
                "Vasia",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static AssetDto one() {
        return new AssetDto(
                1L,
                "Owner",
                "Vasia",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static AssetDto two() {
        return new AssetDto(
                1L,
                "Owner",
                "Vasia",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static AssetDto three() {
        return new AssetDto(
                1L,
                "Owner",
                "Vasia",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static AssetDto four() {
        return new AssetDto(
                1L,
                "Owner",
                "Vasia",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static AssetDto five() {
        return new AssetDto(
                1L,
                "Owner",
                "Vasia",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static List<AssetDto> ASSETS = List.of(
            one(), two(), three(), four(), five()
    );

}
