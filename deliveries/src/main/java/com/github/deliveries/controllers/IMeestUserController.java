package com.github.deliveries.controllers;

import com.github.deliveries.dto.MeestUserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IMeestUserController {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void save(@RequestBody MeestUserDto payload);

    @PutMapping(
            path = "/edit"
    )
    void update(@RequestBody MeestUserDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void remove(@PathVariable(name = "id") Long id);

}
