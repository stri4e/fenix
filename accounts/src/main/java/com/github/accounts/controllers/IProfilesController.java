package com.github.accounts.controllers;

import com.github.accounts.dto.ProfileDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IProfilesController {

    @GetMapping(
            path = "/{profileId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ProfileDto findContact(@PathVariable Long profileId);

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    ProfileDto save(@Valid @RequestBody ProfileDto payload);

    @PutMapping
    void update(@Valid @RequestBody ProfileDto payload);

}
