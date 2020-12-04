package com.github.managers.controllers;

import com.github.managers.dto.CustomerStatisticsDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ICustomerStatisticsController {

    @GetMapping(
            path = "/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerStatisticsDto findById(@PathVariable(name = "customerId") Long customerId);

}
