package com.github.messenger.services;

import com.github.messenger.payload.BroadcastMessage;
import com.github.messenger.payload.SingleMessage;
import infobip.api.model.omni.scenarios.Scenario;
import infobip.api.model.omni.send.OmniResponse;

public interface IOmniService {

    Scenario fetchScenario();

    OmniResponse singleMessage(SingleMessage payload);

    OmniResponse broadcastMessage(BroadcastMessage payload);

}
