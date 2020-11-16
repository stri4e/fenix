package com.github.geolocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -2130958011206257395L;

    @JsonProperty(value = "cities")
    private Map<String, String> cities;

    @JsonProperty(value = "timeZone")
    private String timeZone;

    @JsonProperty(value = "postCode")
    private String postCode;

    @JsonProperty(value = "subdivision")
    private Map<String, String> subdivision;

    @JsonProperty(value = "continentCode")
    private String continentCode;

    @JsonProperty(value = "continents")
    private Map<String, String> continents;

    @JsonProperty(value = "countryCode")
    private String countryCode;

    @JsonProperty(value = "countries")
    private Map<String, String> countries;

}
