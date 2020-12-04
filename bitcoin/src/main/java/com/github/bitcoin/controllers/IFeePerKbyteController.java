package com.github.bitcoin.controllers;

import com.github.bitcoin.dto.FeePerKbDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IFeePerKbyteController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    FeePerKbDto save(@Valid @RequestBody FeePerKbDto payload);

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    FeePerKbDto findActual();

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody FeePerKbDto payload);

}
