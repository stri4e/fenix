package com.github.admins.controllers;

import com.github.admins.dto.NovaposhtaLegalCounterpartyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface INovaposhtaLegalCounterpartyController {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    NovaposhtaLegalCounterpartyDto save(@Valid @RequestBody NovaposhtaLegalCounterpartyDto payload);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    NovaposhtaLegalCounterpartyDto find();

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody NovaposhtaLegalCounterpartyDto payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
