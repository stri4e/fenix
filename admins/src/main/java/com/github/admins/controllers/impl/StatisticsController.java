package com.github.admins.controllers.impl;

import com.github.admins.controllers.IStatisticsController;
import com.github.admins.dto.LoginDto;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.IOrderService;
import com.github.admins.services.IStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController implements IStatisticsController {

    private final IStatisticsService statisticsService;

    private final IOrderService orderService;

    @Override
    public List<OrderDetailDto>
    findOrdersInTime(OrderStatus status, LocalDateTime start, LocalDateTime end) {
        return this.orderService.findByStatusInTime(status, start, end)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<LoginDto> findLoginsInTime(LocalDateTime start, LocalDateTime end) {
        return this.statisticsService.findLoginsInTime(start, end)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<ViewDto> findViewsInTime(LocalDateTime start, LocalDateTime end) {
        return this.statisticsService.findViewsInTime(start, end)
                .orElseThrow(NotFound::new);
    }
}
