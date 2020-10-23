package com.github.products.controllers;

import com.github.products.dto.BrandDto;
import com.github.products.entity.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IBrandController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    BrandDto save(@RequestBody BrandDto payload);

    @GetMapping(
            path = "/fetch/{name}",
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
            path = "/edit"
    )
    void update(@RequestBody BrandDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
