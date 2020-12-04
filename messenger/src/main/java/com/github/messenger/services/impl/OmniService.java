package com.github.messenger.services.impl;

import com.github.messenger.payload.BroadcastMessage;
import com.github.messenger.payload.SingleMessage;
import com.github.messenger.services.IOmniService;
import com.github.messenger.utils.Logging;
import com.google.common.collect.Lists;
import infobip.api.client.CreateScenario;
import infobip.api.client.SendAdvancedOmniMessage;
import infobip.api.model.omni.Destination;
import infobip.api.model.omni.OmniChannel;
import infobip.api.model.omni.To;
import infobip.api.model.omni.scenarios.Scenario;
import infobip.api.model.omni.scenarios.Step;
import infobip.api.model.omni.send.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OmniService implements IOmniService {

    private final CreateScenario createScenario;

    private final SendAdvancedOmniMessage sendAdvancedOmniMessage;

    @Value(value = "${app.company.label}")
    private String label;

    private Scenario scenarioInstance;

    @Logging
    @Override
    public Scenario fetchScenario() {
        if (Objects.isNull(this.scenarioInstance)) {
            Step viberStep = new Step();
            viberStep.setFrom(this.label);
            viberStep.setChannel(OmniChannel.VIBER);
            Step smsStep = new Step();
            smsStep.setFrom(this.label);
            smsStep.setChannel(OmniChannel.SMS);
            List<Step> steps = Lists.newArrayList(viberStep, smsStep);
            Scenario scenarioBody = new Scenario();
            scenarioBody.setName(this.label + ": SMS and Viber");
            scenarioBody.setFlow(steps);
            scenarioBody.setDefaultScenario(Boolean.FALSE);
            this.scenarioInstance = this.createScenario.execute(scenarioBody);
        }
        return this.scenarioInstance;
    }

    @Logging
    @Override
    public OmniResponse singleMessage(SingleMessage payload) {
        return sendMessage(payload.getText(),
                payload.getLanguageCode(),
                payload.getTransliteration(),
                payload.getPhoneNumber());
    }

    @Logging
    @Override
    public OmniResponse broadcastMessage(BroadcastMessage payload) {
        return sendMessage(payload.getText(),
                payload.getLanguageCode(),
                payload.getTransliteration(),
                payload.getPhoneNumber().toArray(String[]::new)
        );
    }

    private OmniResponse sendMessage(String text, String languageCode, String transliteration, String... phones) {
        OmniAdvancedRequest omniAdvancedRequest = new OmniAdvancedRequest();
        omniAdvancedRequest.setDestinations(getDestinations(Lists.newArrayList(phones)));
        omniAdvancedRequest.setScenarioKey(fetchScenario().getKey());
        omniAdvancedRequest.setSms(getSmsData(
                text,
                languageCode,
                transliteration
        ));
        omniAdvancedRequest.setViber(getViberData(text));
        return this.sendAdvancedOmniMessage.execute(omniAdvancedRequest);
    }

    private List<Destination> getDestinations(List<String> phones) {
        return phones.stream().map(number -> {
            To to = new To();
            to.setPhoneNumber(number);
            Destination destination = new Destination();
            destination.setTo(to);
            return destination;
        }).collect(Collectors.toList());
    }

    private ViberData getViberData(String text) {
        ViberData viberData = new ViberData();
        viberData.setText(text);
        return viberData;
    }

    private SmsData getSmsData(String text, String languageCode, String transliteration) {
        SmsData smsData = new SmsData();
        smsData.setText(text);
        Language language = new Language();
        language.setLanguageCode(languageCode);
        smsData.setLanguage(language);
        smsData.setTransliteration(transliteration);
        return smsData;
    }

}
