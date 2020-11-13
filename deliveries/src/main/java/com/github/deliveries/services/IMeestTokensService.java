package com.github.deliveries.services;

import com.github.deliveries.payload.MeestAuthResponse;
import com.github.deliveries.payload.MeestUserRequest;

import java.util.Map;

public interface IMeestTokensService {

    MeestAuthResponse newSession(MeestUserRequest payload);

    MeestAuthResponse refreshSession(Map<String, String> payload);

}
