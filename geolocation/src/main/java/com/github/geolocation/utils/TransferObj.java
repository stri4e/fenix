package com.github.geolocation.utils;

import com.github.geolocation.dto.GeolocationDto;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Subdivision;

import java.util.List;

public class TransferObj {

    public static GeolocationDto fromCityResponse(CityResponse data) {
        Subdivision subdivision;
        List<Subdivision> subdivisions = data.getSubdivisions();
        if (subdivisions.iterator().hasNext()) {
            subdivision = data.getSubdivisions().iterator().next();
        } else {
            subdivision = new Subdivision();
        }
        return new GeolocationDto(
                data.getCity().getNames(),
                data.getLocation().getTimeZone(),
                data.getPostal().getCode(),
                subdivision.getNames(),
                data.getContinent().getCode(),
                data.getContinent().getNames(),
                data.getCountry().getIsoCode(),
                data.getCountry().getNames()
        );
    }

}
