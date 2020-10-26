package com.github.orders.controllers;

import com.github.orders.dto.BranchDto;
import com.github.orders.entity.Branch;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IBranchCompanyController {

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    BranchDto findById(@PathVariable Long id);

    @PostMapping(
            path = "/edit/{companyId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    BranchDto save(
            @PathVariable(name = "companyId") Long companyId,
            @Valid @RequestBody BranchDto payload);

    @PutMapping(
            path = "/edit"
    )
    void update(@Valid @RequestBody BranchDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void delete(@PathVariable(name = "id") Long id);

}
