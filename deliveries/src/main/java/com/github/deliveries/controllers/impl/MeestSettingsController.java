package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.IMeestSettingsController;
import com.github.deliveries.dto.MeestSettingsDto;
import com.github.deliveries.entity.MeestSession;
import com.github.deliveries.entity.MeestSettings;
import com.github.deliveries.entity.MeestUser;
import com.github.deliveries.payload.MeestAuthResponse;
import com.github.deliveries.payload.MeestTokens;
import com.github.deliveries.payload.MeestUserRequest;
import com.github.deliveries.services.IMeestSessionService;
import com.github.deliveries.services.IMeestSettingsService;
import com.github.deliveries.services.IMeestTokensService;
import com.github.deliveries.services.IMeestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.github.deliveries.utils.TransferObj.fromMeestSettings;
import static com.github.deliveries.utils.TransferObj.toMeestSettings;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/meest")
public class MeestSettingsController implements IMeestSettingsController {

    private final IMeestSettingsService meestSettingsService;

    private final IMeestTokensService meestTokensService;

    private final IMeestSessionService meestSessionService;

    private final IMeestUserService meestUserService;

    @Override
    public MeestSettingsDto save(MeestSettingsDto payload) {
        return fromMeestSettings(
                this.meestSettingsService.create(toMeestSettings(payload))
        );
    }

    @Override
    public MeestSettingsDto clientSession() {
        MeestSettings settings = this.meestSettingsService.read();
        MeestSession session = settings.getSession();
        if (Objects.nonNull(session) && session.hashTokens()) {
            expired(settings, session);
        } else {
            newSession(settings);
        }
        return fromMeestSettings(settings);
    }

    private void newSession(MeestSettings settings) {
        MeestUser user = this.meestUserService.read();
        MeestUserRequest authReq = new MeestUserRequest(user.getLogin(), user.getPass());
        MeestAuthResponse response = this.meestTokensService.newSession(authReq);
        MeestTokens token = response.getResult();
        MeestSession tmp = new MeestSession(
                token.getToken(),
                token.getRefreshToken(),
                token.getExpiresIn()
        );
        MeestSession session = this.meestSessionService.create(tmp);
        settings.setSession(session);
        this.meestSettingsService.update(settings);
    }

    private void expired(MeestSettings settings, MeestSession session) {
        if (session.isExpired()) {
            var refreshToken = session.getRefreshToken();
            Map<String, String> rtm = new HashMap<>();
            rtm.put("refreshToken", refreshToken);
            MeestAuthResponse response = this.meestTokensService.refreshSession(rtm);
            MeestTokens token = response.getResult();
            MeestSession tmp = new MeestSession(
                    token.getToken(),
                    token.getRefreshToken(),
                    token.getExpiresIn()
            );
            session = this.meestSessionService.create(tmp);
            settings.setSession(session);
            this.meestSettingsService.update(settings);
        }
    }

    @Override
    public void update(MeestSettingsDto payload) {
        MeestSettings meest = this.meestSettingsService.read();
        meest.setBaseUrl(payload.getBaseUrl());
        meest.setHeader(payload.getHeader());
        this.meestSettingsService.update(meest);
    }

    @Override
    public void remove(Long id) {
        this.meestSettingsService.remove(id);
    }

}
