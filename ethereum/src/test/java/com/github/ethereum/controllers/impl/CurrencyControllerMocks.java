package com.github.ethereum.controllers.impl;

import com.github.ethereum.dto.CurrencyDto;
import com.github.ethereum.entity.Currency;

public class CurrencyControllerMocks {

    public static CurrencyDto request() {
        return new CurrencyDto(
            1L, "BTC", "Bitcoin", "a-z", 8
        );
    }

    public static CurrencyDto response() {
        return new CurrencyDto(
                1L, "BTC", "Bitcoin", "a-z", 8
        );
    }

    public static Currency wrongCurrency() {
        return new Currency(
                "BTC", "Bitcoin", "a-z", 7
        );
    }

    public static Currency currencyForSave() {
        return new Currency(
                "BTC", "Bitcoin", "a-z", 8
        );
    }

}
