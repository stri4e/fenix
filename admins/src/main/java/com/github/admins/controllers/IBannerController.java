package com.github.admins.controllers;

import com.github.admins.dto.BannerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IBannerController {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    BannerDto save(@Valid @RequestBody BannerDto payload);

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody BannerDto payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
