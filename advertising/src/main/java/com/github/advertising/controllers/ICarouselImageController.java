package com.github.advertising.controllers;

import com.github.advertising.dto.CarouselImageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ICarouselImageController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<CarouselImageDto> findAllActive();

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    CarouselImageDto save(@Valid @RequestBody CarouselImageDto payload);

    @PutMapping(path = "/edit")
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody CarouselImageDto payload);

    @DeleteMapping(path = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
