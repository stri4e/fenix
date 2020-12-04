package com.github.geolocation.service;

import com.maxmind.geoip2.model.CityResponse;

public interface IGeolocationService {

    CityResponse read(String ip);

}
