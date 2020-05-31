package com.github.statistics.services.impl;

import com.github.statistics.repository.UsersStatisticsRepository;
import com.github.statistics.services.IUsersStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersStatisticsService implements IUsersStatisticsService {

    private final UsersStatisticsRepository usersRepository;

}
