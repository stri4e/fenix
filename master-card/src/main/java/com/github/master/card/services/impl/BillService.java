package com.github.master.card.services.impl;

import com.github.master.card.services.IBillService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class BillService implements IBillService {
    @Override
    public void update(Long billId, BigInteger value, String transfer) {
    }
}
