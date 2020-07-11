package com.github.statistics.controllers;

import com.github.statistics.dto.UsersStatisticsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IUsersStatisticsController {

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    UsersStatisticsDto create(@RequestBody Long payload);

    @GetMapping(
            path = "/{statisticId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    UsersStatisticsDto readById(@PathVariable Long statisticId);

    @GetMapping(
            path = "/current/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    UsersStatisticsDto readByUserId(@PathVariable Long userId);

    @PutMapping(
            path = "/{userId}"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void update(@PathVariable Long userId);

}
