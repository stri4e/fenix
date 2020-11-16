package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.NovaposhtaSettingsDto;
import com.github.deliveries.entity.NovaposhtaSettings;

import java.util.HashMap;
import java.util.Map;

public class NovaposhtaSettingsControllerMocks {

    public static NovaposhtaSettings novaposhtaSettingsForSave() {
        Map<String, String> headers = new HashMap<>();
        headers.put("json", "application/json");
        return new NovaposhtaSettings(
                null,
                "http://novaposhta.com.ua",
                "a44f99b403bcd7cf61673fc0446b81d7",
                headers
        );
    }

    public static NovaposhtaSettingsDto request() {
        Map<String, String> headers = new HashMap<>();
        headers.put("json", "application/json");
        return new NovaposhtaSettingsDto(
                null,
                "http://novaposhta.com.ua",
                "a44f99b403bcd7cf61673fc0446b81d7",
                headers
        );
    }

    public static NovaposhtaSettingsDto response() {
        Map<String, String> headers = new HashMap<>();
        headers.put("json", "application/json");
        return new NovaposhtaSettingsDto(
                1L,
                "http://novaposhta.com.ua",
                "a44f99b403bcd7cf61673fc0446b81d7",
                headers
        );
    }

    public static NovaposhtaSettingsDto requestForUpdate() {
        Map<String, String> headers = new HashMap<>();
        headers.put("json", "application/json");
        return new NovaposhtaSettingsDto(
                1L,
                "http://novaposhta.com",
                "a44f99b403bcd7cf61673fc0446b81d7",
                headers
        );
    }

}
