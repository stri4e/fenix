package com.github.ethereum.controllers.impl;

import com.github.ethereum.dto.FeeDto;
import com.github.ethereum.entity.Fee;

import java.math.BigInteger;

public class FeeControllerMocks {

    public static Fee fee() {
        return new Fee(
                BigInteger.TEN,
                BigInteger.TEN
        );
    }

    public static FeeDto expected() {
        return new FeeDto(
                BigInteger.TEN,
                BigInteger.TEN
        );
    }

}
