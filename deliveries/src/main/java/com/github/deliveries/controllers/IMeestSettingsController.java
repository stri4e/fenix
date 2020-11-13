package com.github.deliveries.controllers;

import com.github.deliveries.dto.MeestSettingsDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IMeestSettingsController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    MeestSettingsDto save(@RequestBody MeestSettingsDto settings);

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    MeestSettingsDto clientSession();

    @PutMapping(
            path = "/edit"
    )
    void update(@RequestBody MeestSettingsDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void remove(@PathVariable(name = "id") Long id);

}
