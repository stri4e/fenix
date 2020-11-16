package com.github.admins.services.impl;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.services.IStatisticsService;

import java.util.List;
import java.util.Optional;

public class StatisticsService implements IStatisticsService {

    @Override
    public Optional<List<LoginDto>> findLoginsInTime(String start, String end) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ViewDto>> findViewsInTime(String start, String end) {
        return Optional.empty();
    }
}
