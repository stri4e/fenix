package com.github.payments.controllers;

import com.github.payments.dto.CurrentRateDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ICurrentRatesController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<CurrentRateDto> findAll();

}
