package com.github.managers.controllers;

import com.github.managers.dto.NovaposhtaLegalCounterpartyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface INovaposhtaLegalCounterpartyController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    NovaposhtaLegalCounterpartyDto find();

}
