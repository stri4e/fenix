package com.github.deliveries.services.impl;

import com.github.deliveries.payload.MeestAuthResponse;
import com.github.deliveries.payload.MeestTokens;
import com.github.deliveries.payload.MeestUserRequest;
import com.github.deliveries.services.IMeestTokensService;

import java.util.Map;

// This service is mocks. Because has not contract with meest
public class MeestTokensService implements IMeestTokensService {

    @Override
    public MeestAuthResponse newSession(MeestUserRequest payload) {
        MeestTokens token = new MeestTokens(
                "54b542445faca198cf1180d00c22cc7d",
                "a44f99b403bcd7cf61673fc0446b81d7",
                System.currentTimeMillis() + 86400000
        );
        return new MeestAuthResponse(
                "OK",
                "",
                "",
                "",
                token
        );
    }

    @Override
    public MeestAuthResponse refreshSession(Map<String, String> payload) {
        MeestTokens token = new MeestTokens(
                "54b542445faca198cf1180d00c22cc7d",
                "a44f99b403bcd7cf61673fc0446b81d7",
                System.currentTimeMillis() + 86400000
        );
        return new MeestAuthResponse(
                "OK",
                "",
                "",
                "",
                token
        );
    }

}
