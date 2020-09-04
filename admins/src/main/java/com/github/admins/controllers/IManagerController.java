package com.github.admins.controllers;

import com.github.admins.dto.ManagerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IManagerController {

    @GetMapping(
            path = "/v1/fetch/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    ManagerDto findManager(@PathVariable(name = "orderId") Long orderId);

}
