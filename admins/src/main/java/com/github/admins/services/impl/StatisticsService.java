package com.github.admins.services.impl;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.services.IStatisticsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class StatisticsService implements IStatisticsService {

    @Override
    public Optional<List<LoginDto>> findLoginsInTime(LocalDateTime start, LocalDateTime end) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ViewDto>> findViewsInTime(LocalDateTime start, LocalDateTime end) {
        return Optional.empty();
    }
}
