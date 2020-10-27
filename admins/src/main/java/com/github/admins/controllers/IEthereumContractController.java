package com.github.admins.controllers;

import com.github.admins.dto.EthereumContractDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IEthereumContractController {

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    EthereumContractDto save(@Valid @RequestBody EthereumContractDto payload);

    @PutMapping(
            path = "/"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody EthereumContractDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
