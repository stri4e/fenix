package com.github.admins.controllers;

import com.github.admins.dto.BranchDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IBranchCompanyController {

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    BranchDto findById(@PathVariable Long id);

    @PostMapping(
            path = "/{companyId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    BranchDto save(
            @PathVariable(name = "companyId") Long companyId,
            @Valid @RequestBody BranchDto payload
    );

    @PutMapping
    void update(@Valid @RequestBody BranchDto payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
