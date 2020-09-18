package com.github.orders.controllers;

import com.github.orders.dto.CompanyDto;
import com.github.orders.entity.Company;
import com.github.orders.entity.CompanyStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IDeliveryCompaniesController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<CompanyDto> findAll();

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Company save(@RequestBody Company payload);

    @GetMapping(
            path = "/fetch/{id}"
    )
    Company findById(@PathVariable(name = "id") Long id);

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody Company payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
