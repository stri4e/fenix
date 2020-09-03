package com.github.admins.controllers.impl;

import com.github.admins.controllers.IStatisticsController;
import com.github.admins.dto.LoginDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.IOrderService;
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

    private final IOrderService orderService;

    @Override
    public Object findOrders(OrderStatus status, Integer page, Integer size) {
        return this.orderService.pageByStatus(status, page, size);
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
