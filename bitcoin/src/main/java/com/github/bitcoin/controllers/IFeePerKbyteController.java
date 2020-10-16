package com.github.bitcoin.controllers;

import com.github.bitcoin.dto.FeePerKbDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IFeePerKbyteController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FeePerKbDto save(FeePerKbDto payload);

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FeePerKbDto findActual();

    @PutMapping(
            path = "/edit"
    )
    void update(FeePerKbDto payload);

}
