package com.github.managers.services.impl;

import com.github.managers.payload.SingleMessage;
import com.github.managers.services.ISMSService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SMSService implements ISMSService {
    @Override
    public void singleSMS(@Valid SingleMessage payload) {

    }
}
