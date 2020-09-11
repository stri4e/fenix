package com.github.orders.controllers;

import com.github.orders.entity.Branch;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IBranchCompanyController {

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Branch findById(@PathVariable Long id);

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Branch save(@Valid @RequestBody Branch payload);

    @PutMapping(
            path = "/edit"
    )
    void update(@Valid @RequestBody Branch payload);

    @DeleteMapping(
            path = "/edit"
    )
    void delete(@PathVariable Long id);

}
