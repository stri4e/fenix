package com.github.master.card.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Converters {

    private static final int POINT = 8;

    public static BigInteger toCoin(BigDecimal amount) {
        return amount.movePointRight(POINT).toBigInteger();
    }

    public static BigDecimal fromCoin(BigInteger amount) {
        return new BigDecimal(amount).movePointLeft(POINT);
    }

}
