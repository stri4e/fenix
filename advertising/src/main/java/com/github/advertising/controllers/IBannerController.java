package com.github.advertising.controllers;

import com.github.advertising.dto.BannerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IBannerController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    BannerDto findActive();

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    BannerDto save(@Valid @RequestBody BannerDto payload);

    @PutMapping(path = "/edit")
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody BannerDto payload);

    @DeleteMapping(path = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
