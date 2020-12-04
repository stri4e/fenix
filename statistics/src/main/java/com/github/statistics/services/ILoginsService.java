package com.github.statistics.services;

import com.github.statistics.entity.Login;

public interface ILoginsService {

    Login create(Login login);

    Long countByLastHour();

}
