package com.github.admins.controllers.impl;

import com.github.admins.dto.FilterDto;

public class FiltersControllerMocks {

    public static FilterDto request() {
        return new FilterDto(
                null, "title1", null
        );
    }

    public static FilterDto response() {
        return new FilterDto(
                1L, "title1", null
        );
    }

}
