package com.github.admins.controllers;

import com.github.admins.dto.FilterDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IFiltersController {

    @PostMapping(
            path = "/{subcategoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FilterDto save(
            @PathVariable(name = "subcategoryName") String subcategoryName,
            @RequestBody FilterDto payload
    );

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FilterDto findById(@PathVariable(name = "id") Long id);

    @PutMapping(
            path = "/"
    )
    void update(@RequestBody FilterDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    void remove(@PathVariable(name = "id") Long id);

}
