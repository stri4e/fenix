package com.github.bitcoin.services;

import com.github.bitcoin.entity.FeePerKb;

public interface IFeePerKbService {

    FeePerKb create(FeePerKb feePerKb);

    FeePerKb readActual();

    void update(FeePerKb feePerKb);

}
