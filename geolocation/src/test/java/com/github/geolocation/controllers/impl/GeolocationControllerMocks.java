package com.github.geolocation.controllers.impl;

import com.github.geolocation.dto.GeolocationDto;

import java.util.HashMap;
import java.util.Map;

public class GeolocationControllerMocks {

    public static GeolocationDto response() {
        return new GeolocationDto(
                cities(),
                "Asia/Bangkok",
                "11000",
                subdivision(),
                "AS",
                continents(),
                "TH",
                countries()
        );
    }

    public static Map<String, String> cities() {
        Map<String, String> cities = new HashMap<>();
        cities.put("en", "Nonthaburi");
        cities.put("ru", "Нонтхабури");
        return cities;
    }

    public static Map<String, String> subdivision() {
        Map<String, String> cities = new HashMap<>();
        cities.put("en", "Nonthaburi");
        return cities;
    }

    public static Map<String, String> continents() {
        Map<String, String> cities = new HashMap<>();
        cities.put("de", "Asien");
        cities.put("ru", "Азия");
        cities.put("pt-BR", "Ásia");
        cities.put("ja", "アジア");
        cities.put("en", "Asia");
        cities.put("fr", "Asie");
        cities.put("zh-CN", "亚洲");
        cities.put("es", "Asia");
        return cities;
    }

    public static Map<String, String> countries() {
        Map<String, String> cities = new HashMap<>();
        cities.put("de", "Thailand");
        cities.put("ru", "Тайланд");
        cities.put("pt-BR", "Tailândia");
        cities.put("ja", "タイ王国");
        cities.put("en", "Thailand");
        cities.put("fr", "Thaïlande");
        cities.put("zh-CN", "泰国");
        cities.put("es", "Tailandia");
        return cities;
    }

}
