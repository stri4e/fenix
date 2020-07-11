package com.github.statistics.services;

import com.github.statistics.entity.UsersStatistics;

import java.util.Date;

public interface IUsersStatisticsService {

    UsersStatistics create(UsersStatistics usersStatistics);

    UsersStatistics readById(Long id);

    UsersStatistics readByUserId(Long userId);

    void update(Long userId);

}
