package com.github.deliveries.services;

import com.github.deliveries.entity.MeestSettings;

public interface IMeestSettingsService {

    MeestSettings create(MeestSettings meestSettings);

    MeestSettings read();

    void update(MeestSettings settings);

    void remove(Long id);

}
