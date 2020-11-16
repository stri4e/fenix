package com.github.admins.controllers;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.payload.OrderStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IStatisticsController {

    @GetMapping(
            path = "/orders/{status}/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetailDto> findOrdersInTime(
            @PathVariable(name = "status") OrderStatus status,
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end
    );

    @GetMapping(
            path = "/logins/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<LoginDto> findLoginsInTime(
            @RequestParam String start,
            @RequestParam String end
    );

    @GetMapping(
            path = "/views/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<ViewDto> findViewsInTime(
            @RequestParam String start,
            @RequestParam String end
    );

}
