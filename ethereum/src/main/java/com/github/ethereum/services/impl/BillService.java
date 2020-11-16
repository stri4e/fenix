package com.github.ethereum.services.impl;

import com.github.ethereum.payload.Report;
import com.github.ethereum.services.IBillService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class BillService implements IBillService {

    @Override
    public Report update(String address, BigInteger value, String transfer) {
        return null;
    }

}
