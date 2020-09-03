package com.github.admins.services.impl;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.PurchaseDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.services.IStatisticsService;

import java.util.List;

public class StatisticsService implements IStatisticsService {

    @Override
    public List<OrderDetailDto> findPurchases(String start, String end) {
        return null;
    }

    @Override
    public List<LoginDto> findLogins(String start, String end) {
        return null;
    }

    @Override
    public List<ViewDto> findViews(String start, String end) {
        return null;
    }

}
