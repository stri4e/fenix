package com.github.ethereum.services;

import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.entity.Fee;

import java.math.BigInteger;

public interface IFeeService {

    Fee create(Fee fee);

    Fee readByStatus(EntityStatus status);

    void update(
            BigInteger fee,
            BigInteger gasPrice,
            EntityStatus status
    );

}
