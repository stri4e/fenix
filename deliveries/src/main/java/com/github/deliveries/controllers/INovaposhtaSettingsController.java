package com.github.deliveries.controllers;

import com.github.deliveries.dto.NovaposhtaSettingsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface INovaposhtaSettingsController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    NovaposhtaSettingsDto save(@Valid @RequestBody NovaposhtaSettingsDto payload);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    NovaposhtaSettingsDto find();

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody NovaposhtaSettingsDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
