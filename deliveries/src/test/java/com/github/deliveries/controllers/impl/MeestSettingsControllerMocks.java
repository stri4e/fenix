package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.MeestSettingsDto;
import com.github.deliveries.entity.MeestSession;
import com.github.deliveries.entity.MeestSettings;
import com.github.deliveries.entity.MeestUser;

import java.util.HashMap;
import java.util.Map;

public class MeestSettingsControllerMocks {

    public static MeestSettingsDto request() {
        Map<String, String> header = new HashMap<>();
        header.put("json", "application/json");
        return new MeestSettingsDto(
                null,
                "http://meest.com.us",
                "a44f99b403bcd7cf61673fc0446b81d7",
                header
        );
    }

    public static MeestSettingsDto response() {
        Map<String, String> header = new HashMap<>();
        header.put("json", "application/json");
        return new MeestSettingsDto(
                1L,
                "http://meest.com.us",
                null,
                header
        );
    }

    public static MeestSettingsDto requestForUpdate() {
        Map<String, String> header = new HashMap<>();
        header.put("json", "application/json");
        return new MeestSettingsDto(
                null,
                "http://meest.com",
                "a44f99b403bcd7cf61673fc0446b81d7",
                header
        );
    }

    public static MeestSettings meestSettingsForSave() {
        Map<String, String> header = new HashMap<>();
        header.put("json", "application/json");
        return new MeestSettings(
                "http://meest.com.us",
                header
        );
    }

    public static MeestSettings meestSettings() {
        Map<String, String> header = new HashMap<>();
        header.put("json", "application/json");
        return new MeestSettings(
                "http://meest.com.us",
                header
        );
    }

    public static MeestSettingsDto responseForNewSession() {
        Map<String, String> header = new HashMap<>();
        header.put("json", "application/json");
        return new MeestSettingsDto(
                1L,
                "http://meest.com.us",
                "54b542445faca198cf1180d00c22cc7d",
                header
        );
    }

    public static MeestUser meestUserForSave() {
        return new MeestUser(
                "mest_user",
                "meest_password"
        );
    }

    public static MeestSession meestSession() {
        return new MeestSession(
                "54b542445faca198cf1180d00c22cc7d",
                "a44f99b403bcd7cf61673fc0446b81d7",
                System.currentTimeMillis() - 86400000
        );
    }

}
