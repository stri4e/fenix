package com.github.managers.controllers.impl;

import com.github.managers.controllers.ISMSController;
import com.github.managers.payload.SingleMessage;
import com.github.managers.services.ISMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/sms")
public class SMSController implements ISMSController {

    private final ISMSService smsService;

    @Override
    public void singleSMS(@Valid SingleMessage payload) {
        this.smsService.singleSMS(payload);
    }

}
