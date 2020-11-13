package com.github.deliveries.services;

import com.github.deliveries.entity.MeestSession;

import java.util.Date;

public interface IMeestSessionService {

    MeestSession create(MeestSession session);

    MeestSession read();

    void update(Long id, String token, String refreshToken, Date expireIn);

    void remove(Long id);

}
