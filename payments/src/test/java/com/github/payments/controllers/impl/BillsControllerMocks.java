package com.github.payments.controllers.impl;

import com.github.payments.dto.BillDto;
import com.github.payments.dto.WhoDto;
import com.github.payments.dto.WhomDto;
import com.github.payments.entity.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.UUID;

public class BillsControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static final String ADDRESS_ETH = "1GmC16TUvaAHGouzbJ2pWD3fS7x1Mk95V5";

    public static final BigInteger VALUE_ETH = BigInteger.TEN;

    public static final String TRANSFER_HASH = "cb20e7f9c10d8b64165b674b40385728799b9f8414291b9ade9d9a4d909718f8";

    public static BillDto request() {
        return new BillDto(
                null,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(50L),
                "eth",
                ADDRESS_ETH,
                new ArrayList<>(),
                "crypto",
                BillType.def,
                new WhoDto(
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

    public static BillDto response() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(50L),
                "eth",
                ADDRESS_ETH,
                new ArrayList<>(),
                "crypto",
                BillType.def,
                new WhoDto(
                        1L,
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        1L,
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

    public static PaymentTypes paymentTypesForSave() {
        return new PaymentTypes(
                null, "crypto"
        );
    }

    public static Asset assetForSave() {
        return new Asset(
                "Owner",
                "eth",
                "Ethereum",
                8,
                AssetType.crypto
        );
    }

    public static Alias aliasForSave(Bill bill) {
        return new Alias(
                bill,
                USER_ID
        );
    }

    public static Whom whomForSave() {
        return new Whom(
                "Kolia",
                "Zupkin",
                "Shmiga"
        );
    }

    public static Who whoForSave() {
        return new Who(
                "Vasia",
                "Pupkin",
                "Galicin"
        );
    }

    public static Bill billForSave(Asset asset, PaymentTypes types, Who who, Whom whom) {
        return new Bill(
                BigInteger.valueOf(60L),
                BigInteger.valueOf(50L),
                asset,
                ADDRESS_ETH,
                new ArrayList<>(),
                types,
                BillType.def,
                who,
                whom
        );
    }

}
