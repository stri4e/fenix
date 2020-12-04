package com.github.users.center.services;

import com.github.users.center.entity.UserAlias;

import java.util.UUID;

public interface IUserAliasService {

    void save(UserAlias userAlias);

    String alias(UUID userId);

}
