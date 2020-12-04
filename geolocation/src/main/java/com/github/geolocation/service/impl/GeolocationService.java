package com.github.geolocation.service.impl;

import com.github.geolocation.exceptions.BadRequest;
import com.github.geolocation.exceptions.NotFound;
import com.github.geolocation.service.IGeolocationService;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;

@Service
@RequiredArgsConstructor
public class GeolocationService implements IGeolocationService {

    private final DatabaseReader databaseReader;

    @Override
    public CityResponse read(String ip) {
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            return this.databaseReader.city(ipAddress);
        } catch (GeoIp2Exception e) {
            throw new NotFound();
        } catch (IOException e) {
            throw new BadRequest();
        }
    }

}
