package com.github.config.server.service;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.KeysRoles;

import java.util.List;

public interface IKeysRolesService {

    KeysRoles create(KeysRoles ks);

    List<KeysRoles> readAll(EntityStatus status);

    void update(KeysRoles ks);

    void updateStatus(Long id, EntityStatus status);

    void delete(Long id);

}
