package com.github.admins.controllers;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.payload.OrderStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IStatisticsController {

    @GetMapping(
            path = "/orders/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Object findOrders(
            @PathVariable(name = "status") OrderStatus status,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    );

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
