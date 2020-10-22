package com.github.payments.service.impl;

import com.github.payments.dto.BillDto;
import com.github.payments.service.IBillPushService;
import org.springframework.stereotype.Service;

@Service
public class BillPushService implements IBillPushService {
    @Override
    public void billNotify(String ending, BillDto payload) {

    }
}
