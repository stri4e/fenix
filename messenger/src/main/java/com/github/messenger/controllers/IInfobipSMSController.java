package com.github.messenger.controllers;

import com.github.messenger.payload.BroadcastMessage;
import com.github.messenger.payload.BroadcastReport;
import com.github.messenger.payload.SingleMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IInfobipSMSController {

    @PostMapping(
            path = "/single/edit"
    )
    void singleSMS(@Valid @RequestBody SingleMessage payload);

    @PostMapping(
            path = "/broadcast/edit"
    )
    BroadcastReport broadcastSMS(@Valid @RequestBody BroadcastMessage payload);

}
