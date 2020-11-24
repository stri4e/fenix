package com.github.statistics.services.impl;

import com.github.statistics.entity.Login;
import com.github.statistics.repository.LoginsRepo;
import com.github.statistics.services.ILoginsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginsService implements ILoginsService {

    public final LoginsRepo loginsRepo;

    @Override
    public Login create(Login login) {
        return this.loginsRepo.save(login);
    }

    @Override
    public Long countByLastHour() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime hourAgo = now.minus(1, ChronoUnit.HOURS);
        return this.loginsRepo.countAllByCreateAtBetween(now, hourAgo);
    }


}
