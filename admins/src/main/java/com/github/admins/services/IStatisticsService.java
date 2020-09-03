package com.github.admins.services;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.PurchaseDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.services.impl.StatisticsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "statistics",
        fallback = StatisticsService.class,
        contextId = "statisticsId"
)
public interface IStatisticsService {

    @GetMapping(
            path = "/v1/purchases/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetailDto> findPurchases(@RequestParam String start, @RequestParam String end);

    @GetMapping(
            path = "/v1/logins/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<LoginDto> findLogins(@RequestParam String start, @RequestParam String end);

    @GetMapping(
            path = "/v1/views/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<ViewDto> findViews(@RequestParam String start, @RequestParam String end);

}
