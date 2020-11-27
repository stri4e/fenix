package com.github.messenger.controllers.impl;

import com.github.messenger.controllers.IInfobipSMSController;
import com.github.messenger.exceptions.BadRequest;
import com.github.messenger.payload.BroadcastMessage;
import com.github.messenger.payload.BroadcastReport;
import com.github.messenger.payload.SingleMessage;
import com.github.messenger.services.IOmniService;
import com.github.messenger.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import infobip.api.model.omni.To;
import infobip.api.model.omni.send.OmniResponse;
import infobip.api.model.omni.send.OmniResponseDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.messenger.utils.FilterUtils.FAILURE;
import static com.github.messenger.utils.FilterUtils.SUCCESS;

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
                .filter(SUCCESS)
                .findAny()
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public BroadcastReport broadcastSMS(@Valid BroadcastMessage payload) {
        OmniResponse resp = this.omniService.broadcastMessage(payload);
        List<OmniResponseDetails> messages = resp.getMessages();
        return new BroadcastReport(
                messages.stream()
                        .filter(SUCCESS)
                        .map(OmniResponseDetails::getTo)
                        .map(To::getPhoneNumber)
                        .collect(Collectors.toList()),
                messages.stream()
                        .filter(FAILURE)
                        .map(OmniResponseDetails::getTo)
                        .map(To::getPhoneNumber)
                        .collect(Collectors.toList()),
                payload.getText(),
                payload.getLanguageCode(),
                payload.getTransliteration()
        );
    }

}
