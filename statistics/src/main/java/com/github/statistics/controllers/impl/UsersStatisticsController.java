package com.github.statistics.controllers.impl;

import com.github.statistics.controllers.IUsersStatisticsController;
import com.github.statistics.services.IUsersStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class UsersStatisticsController implements IUsersStatisticsController {

    private final IUsersStatisticsService usersService;



}
