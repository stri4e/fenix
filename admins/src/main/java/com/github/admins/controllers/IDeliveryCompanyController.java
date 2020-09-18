package com.github.admins.controllers;

import com.github.admins.dto.CompanyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IDeliveryCompanyController {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    CompanyDto save(@RequestBody CompanyDto payload);

    @GetMapping(path = "/{id}")
    CompanyDto findById(@PathVariable(name = "id") Long id);

    @PutMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody CompanyDto payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
