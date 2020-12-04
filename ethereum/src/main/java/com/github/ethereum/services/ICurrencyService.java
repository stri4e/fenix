package com.github.ethereum.services;

import com.github.ethereum.entity.Currency;

public interface ICurrencyService {

    void update(Long id, String name, String fullName, String addressRegex, Integer pow);

    Currency read();

}
