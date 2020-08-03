package com.github.users.center.services;

import com.github.users.center.entity.User;

public interface IUserService {

    void create(User u);

    User readById(Long id);

    User readByEmail(String email);

    User readByLogin(String login);

    User readByEmailOrLogin(String email, String login);

    boolean existsByEmailOrLogin(String email, String login);

    void updatePass(String pass, Long id);

    void updateIsEnable(boolean isEnable, Long id);

}
