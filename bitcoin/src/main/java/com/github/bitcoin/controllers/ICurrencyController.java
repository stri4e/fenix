package com.github.bitcoin.controllers;

import com.github.bitcoin.dto.CurrencyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface ICurrencyController {

    @PutMapping(path = "/edit")
    @ResponseStatus(code = HttpStatus.OK)
    void updateCurrency(@Valid @RequestBody CurrencyDto payload);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    CurrencyDto findCurrency();

}
