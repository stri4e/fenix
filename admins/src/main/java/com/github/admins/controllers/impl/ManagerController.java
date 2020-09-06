package com.github.admins.controllers.impl;

import com.github.admins.controllers.IManagerController;
import com.github.admins.dto.ManagerDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IManagersService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/manager")
public class ManagerController implements IManagerController {

    private final IManagersService managersService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ManagerDto findManager(Long orderId) {
        return this.managersService.findManager(orderId)
                .orElseThrow(NotFound::new);
    }

}
