package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.dto.FeePerKbDto;
import com.github.bitcoin.entity.FeePerKb;

import java.math.BigInteger;

public class FeePerKbyteControllerMocks {

    public static FeePerKbDto request() {
        return new FeePerKbDto(
                null, BigInteger.TEN
        );
    }

    public static FeePerKbDto requestForUpdate() {
        return new FeePerKbDto(
                1L, BigInteger.valueOf(100L)
        );
    }


    public static FeePerKbDto response() {
        return new FeePerKbDto(
                1L, BigInteger.TEN
        );
    }

    public static FeePerKb feePerKbForSave() {
        return new FeePerKb(
                null, BigInteger.TEN
        );
    }

}
