package com.github.bitcoin.services;

import com.github.bitcoin.entity.Currency;

public interface ICurrencyService {

    void update(Currency currency);

    Currency read();

}
