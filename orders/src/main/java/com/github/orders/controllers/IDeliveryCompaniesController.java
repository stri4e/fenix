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
    List<CompanyDto> findCompanies();

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Company saveCompany(@RequestBody Company payload);

    @GetMapping(
            path = "/fetch/{id}"
    )
    Company findCompany(@PathVariable(name = "id") Long id);

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void updateCompany(@RequestBody Company payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteCompany(@PathVariable(name = "id") Long id);

}
