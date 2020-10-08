package com.github.ethereum.services;

import com.github.ethereum.entity.Currency;

public interface ICurrencyService {

    void update(Currency currency);

    Currency read();

}
