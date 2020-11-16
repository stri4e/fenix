package com.github.payments.service;

import com.github.currencies.rates.payload.RatesPayload;

import java.util.Optional;

public interface INetworkService {

    Optional<RatesPayload> fetchRates(String currencyName);

    void rates();

}
