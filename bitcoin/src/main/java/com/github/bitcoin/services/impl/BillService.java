package com.github.bitcoin.services.impl;

import com.github.bitcoin.payload.Report;
import com.github.bitcoin.services.IBillService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class BillService implements IBillService {

    @Override
    public Report update(String address, BigInteger value, String transfer) {
        return null;
    }

}
