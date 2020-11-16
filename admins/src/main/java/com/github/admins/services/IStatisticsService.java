package com.github.admins.services;

import com.github.admins.dto.LoginDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.services.impl.StatisticsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "statistics",
        fallback = StatisticsService.class,
        contextId = "statisticsId"
)
public interface IStatisticsService {

    @GetMapping(
            path = "/v1/logins/fetch/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<LoginDto>> findLoginsInTime(
            @RequestParam String start,
            @RequestParam String end
    );

    @GetMapping(
            path = "/v1/views/fetch/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<ViewDto>> findViewsInTime(
            @RequestParam String start,
            @RequestParam String end
    );

}
