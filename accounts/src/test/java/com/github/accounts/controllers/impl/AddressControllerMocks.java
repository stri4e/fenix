package com.github.accounts.controllers.impl;

import com.github.accounts.dto.AddressDto;
import com.github.accounts.entity.Address;

import java.util.List;
import java.util.UUID;

public class AddressControllerMocks {

    public static AddressDto request() {
        return new AddressDto(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000
        );
    }

    public static AddressDto requestForUpdate() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnipropetrovsc",
                "Julvern",
                1,
                null,
                null,
                49000
        );
    }

    public static AddressDto response() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000
        );
    }

    public static Address addressForSave() {
        return new Address(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000
        );
    }

}
