package com.github.users.center.services;

import com.github.users.center.entity.User;

import java.util.UUID;

public interface IUserService {

    void create(User u);

    User readById(UUID id);

    User readByEmail(String email);

    User readByLogin(String login);

    User readByEmailOrLogin(String email, String login);

    boolean existsByEmailOrLogin(String email, String login);

    void updatePass(String pass, UUID id);

    void updateIsEnable(boolean isEnable, UUID id);

    void updateIsLocked(String email, boolean isLocked);

}
