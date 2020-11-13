package com.github.deliveries.controllers;

import com.github.deliveries.dto.NovaposhtaSettingsDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface INovaposhtaSettingsController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    NovaposhtaSettingsDto save(@RequestBody NovaposhtaSettingsDto payload);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    NovaposhtaSettingsDto find();

    @PutMapping(
            path = "/edit"
    )
    void update(@RequestBody NovaposhtaSettingsDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void remove(@PathVariable(name = "id") Long id);

}
