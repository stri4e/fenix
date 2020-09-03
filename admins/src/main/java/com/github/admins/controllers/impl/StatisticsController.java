package com.github.admins.controllers.impl;

import com.github.admins.controllers.IStatisticsController;
import com.github.admins.dto.LoginDto;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.PurchaseDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.services.IStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController implements IStatisticsController {

    private final IStatisticsService statisticsService;

    @Override
    public List<OrderDetailDto> findOrders(String start, String end) {
        return this.statisticsService.findPurchases(start, end);
    }

    @Override
    public List<LoginDto> findLogins(String start, String end) {
        return this.statisticsService.findLogins(start, end);
    }

    @Override
    public List<ViewDto> findViews(String start, String end) {
        return this.statisticsService.findViews(start, end);
    }
}
