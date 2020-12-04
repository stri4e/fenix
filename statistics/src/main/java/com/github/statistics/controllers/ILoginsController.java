package com.github.statistics.controllers;

import com.github.statistics.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface ILoginsController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void save(@Valid @RequestBody LoginDto payload);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Long countByLastHour();

}
