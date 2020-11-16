package com.github.deliveries.services;

import com.github.deliveries.entity.MeestUser;

public interface IMeestUserService {

    void create(MeestUser meestUser);

    MeestUser read();

    void update(Long id, String login, String pass);

    void remove(Long id);

}
