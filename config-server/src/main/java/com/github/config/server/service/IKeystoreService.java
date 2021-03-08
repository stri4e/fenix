package com.github.config.server.service;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Keystore;

public interface IKeystoreService {

    Keystore create(Keystore keystore);

    Keystore readActive();

    void update(Long id, EntityStatus status);

}
