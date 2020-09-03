package com.github.admins.controllers;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.PurchaseDto;
import com.github.admins.dto.ViewDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IStatisticsController {

    @GetMapping(
            path = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetailDto> findOrders(@RequestParam String start, @RequestParam String end);

    @GetMapping(
            path = "/logins",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<LoginDto> findLogins(@RequestParam String start, @RequestParam String end);

    @GetMapping(
            path = "/views",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<ViewDto> findViews(@RequestParam String start, @RequestParam String end);

}
