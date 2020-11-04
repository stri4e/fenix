package com.github.admins.controllers;

import com.github.admins.dto.FilterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IFiltersController {

    @PostMapping(
            path = "/{subcategoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    FilterDto save(
            @PathVariable(name = "subcategoryName") String subcategoryName,
            @RequestBody FilterDto payload
    );

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FilterDto findById(@PathVariable(name = "id") Long id);

    @PutMapping
    void update(@RequestBody FilterDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
