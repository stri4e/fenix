package com.github.ethereum.controllers;

import com.github.ethereum.dto.ContractDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IContractController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    ContractDto createContract(@Valid @RequestBody ContractDto payload);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<ContractDto> findAll();

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody ContractDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
