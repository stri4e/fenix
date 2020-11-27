package com.github.messenger.controllers.impl;

import com.github.messenger.controllers.IInfobipSMSController;
import com.github.messenger.exceptions.BadRequest;
import com.github.messenger.payload.BroadcastMessage;
import com.github.messenger.payload.SingleMessage;
import com.github.messenger.services.IOmniService;
import com.github.messenger.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import infobip.api.model.omni.send.OmniResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/infobit/sms")
public class InfobipSMSController implements IInfobipSMSController {

    private final IOmniService omniService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void singleSMS(@Valid SingleMessage payload) {
        OmniResponse resp = this.omniService.singleMessage(payload);
        resp.getMessages().stream()
                .map(r -> r.getStatus().getGroupName())
                .filter(name -> "PENDING".equals(name) || "DELIVERED".equals(name))
                .findAny()
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void broadcastSMS(@Valid BroadcastMessage payload) {
        this.omniService.broadcastMessage(payload);
    }

}
