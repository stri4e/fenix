package com.github.config.server.service.impl;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.KeysRoles;
import com.github.config.server.repository.KeysRolesRepo;
import com.github.config.server.service.IKeysRolesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class KeysRolesService implements IKeysRolesService {

    private final KeysRolesRepo keysRolesRepo;

    public KeysRolesService(KeysRolesRepo keysRolesRepo) {
        this.keysRolesRepo = keysRolesRepo;
    }

    @Override
    public KeysRoles create(KeysRoles ks) {
        return this.keysRolesRepo.save(ks);
    }

    @Override
    public List<KeysRoles> readAll(EntityStatus status) {
        return this.keysRolesRepo.findAllByStatus(status);
    }

    @Override
    public void update(KeysRoles ks) {
        this.keysRolesRepo.save(ks);
    }

    @Override
    public void updateStatus(Long id, EntityStatus status) {
        this.keysRolesRepo.update(id, status);
    }

    @Override
    public void delete(Long id) {
        this.keysRolesRepo.update(id, EntityStatus.off);
    }
}
