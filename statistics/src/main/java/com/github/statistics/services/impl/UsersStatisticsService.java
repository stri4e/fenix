package com.github.statistics.services.impl;

import com.github.statistics.entity.UsersStatistics;
import com.github.statistics.exceptions.NotFound;
import com.github.statistics.repository.UsersStatisticsRepository;
import com.github.statistics.services.IUsersStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersStatisticsService implements IUsersStatisticsService {

    private final UsersStatisticsRepository usersRepository;

    @Override
    public UsersStatistics create(UsersStatistics usersStatistics) {
        return this.usersRepository.save(usersStatistics);
    }

    @Override
    public UsersStatistics readById(Long id) {
        return this.usersRepository.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public UsersStatistics readByUserId(Long userId) {
        return this.usersRepository.findByUserId(userId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long userId) {
        var current = this.usersRepository.findByUserId(userId)
                .orElseThrow(NotFound::new);
        current.setLastLoginDate(new Date());
        current.setCount(current.getCount() + 1);
        this.usersRepository.save(current);
    }

}
