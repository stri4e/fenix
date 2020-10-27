package com.github.admins.controllers;

import com.github.admins.dto.CurrencyDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IEthereumCurrencyController {

    @PutMapping(
            path = "/"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody CurrencyDto payload);

}
