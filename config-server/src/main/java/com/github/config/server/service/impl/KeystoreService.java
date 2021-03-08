package com.github.config.server.service.impl;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Keystore;
import com.github.config.server.exceptions.NotFound;
import com.github.config.server.repository.KeystoreRepo;
import com.github.config.server.service.IKeystoreService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class KeystoreService implements IKeystoreService {

    private final KeystoreRepo keystoreRepo;

    public KeystoreService(KeystoreRepo keystoreRepo) {
        this.keystoreRepo = keystoreRepo;
    }

    @Override
    public Keystore create(Keystore keystore) {
        return this.keystoreRepo.save(keystore);
    }

    @Override
    public Keystore readActive() {
        return this.keystoreRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, EntityStatus status) {
        this.keystoreRepo.update(id, status);
    }

}
