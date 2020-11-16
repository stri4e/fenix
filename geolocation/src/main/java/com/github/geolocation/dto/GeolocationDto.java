package com.github.geolocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationDto {

    private Map<String, String> cities;

    private String timeZone;

    private String postCode;

    private Map<String, String> subdivision;

    private String continentCode;

    private Map<String, String> continents;

    private String countryCode;

    private Map<String, String> countries;

}
