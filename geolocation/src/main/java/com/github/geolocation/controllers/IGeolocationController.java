package com.github.geolocation.controllers;

import com.github.geolocation.dto.GeolocationDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

public interface IGeolocationController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    GeolocationDto find(@ApiIgnore @RequestAttribute String ip);

    @GetMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    GeolocationDto findByParams(@RequestParam(name = "ip") String ip);

}
