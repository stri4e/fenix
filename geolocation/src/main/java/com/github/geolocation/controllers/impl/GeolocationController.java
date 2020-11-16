package com.github.geolocation.controllers.impl;

import com.github.geolocation.controllers.IGeolocationController;
import com.github.geolocation.dto.GeolocationDto;
import com.github.geolocation.service.IGeolocationService;
import com.github.geolocation.utils.TransferObj;
import com.maxmind.geoip2.model.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class GeolocationController implements IGeolocationController {

    private final IGeolocationService geolocationService;

    @Override
    public GeolocationDto find(String ip) {
        CityResponse data = this.geolocationService.read(ip);
        return TransferObj.fromCityResponse(data);
    }

    @Override
    public GeolocationDto findByParams(String ip) {
        CityResponse data = this.geolocationService.read(ip);
        return TransferObj.fromCityResponse(data);
    }

}
