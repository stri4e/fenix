package com.github.admins.controllers;

import com.github.admins.dto.BrandDto;
import com.github.admins.payload.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IBrandsController {

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    BrandDto create(@RequestBody BrandDto payload);

    @GetMapping(
            path = "/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    BrandDto findByName(@PathVariable(name = "name") String name);

    @GetMapping(
            path = "/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<BrandDto> findAllByStatus(
            @PathVariable(name = "status") EntityStatus status
    );

    @PutMapping(
            path = "/"
    )
    void update(@RequestBody BrandDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
