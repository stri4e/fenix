package com.github.bitcoin.services;

import com.github.bitcoin.entity.FeePerKb;

import java.math.BigInteger;

public interface IFeePerKbService {

    FeePerKb create(FeePerKb feePerKb);

    FeePerKb readActual();

    void update(Long id, BigInteger fee);

}
