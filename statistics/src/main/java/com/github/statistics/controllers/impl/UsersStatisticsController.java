package com.github.statistics.controllers.impl;

import com.github.statistics.controllers.IUsersStatisticsController;
import com.github.statistics.dto.UsersStatisticsDto;
import com.github.statistics.entity.UsersStatistics;
import com.github.statistics.services.IUsersStatisticsService;
import com.github.statistics.utils.Logging;
import com.github.statistics.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/v1/user")
@RequiredArgsConstructor
public class UsersStatisticsController implements IUsersStatisticsController {

    private final IUsersStatisticsService usersService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public UsersStatisticsDto create(Long payload) {
        var statistic = new UsersStatistics(payload, new Date(), 0);
        return TransferObj.fromUsersStatisticsDto(this.usersService.create(statistic));
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public UsersStatisticsDto readById(Long statisticId) {
        var statistics = this.usersService.readById(statisticId);
        return TransferObj.fromUsersStatisticsDto(statistics);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public UsersStatisticsDto readByUserId(Long userId) {
        var statistic = this.usersService.readByUserId(userId);
        return TransferObj.fromUsersStatisticsDto(statistic);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void update(Long userId) {
        this.usersService.update(userId);
    }

}
