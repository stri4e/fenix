package com.github.deliveries.services;

import com.github.deliveries.entity.NovaposhtaSettings;

public interface INovaposhtaSettingsService {

    NovaposhtaSettings create(NovaposhtaSettings settings);

    NovaposhtaSettings read();

    void update(Long id, String baseUrl, String apiKey);

    void remove(Long id);
}
