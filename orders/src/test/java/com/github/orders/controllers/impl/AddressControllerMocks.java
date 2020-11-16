package com.github.orders.controllers.impl;

import com.github.orders.dto.AddressDto;
import com.github.orders.entity.Address;
import com.github.orders.entity.AddressType;

import java.util.List;
import java.util.UUID;

public class AddressControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static AddressDto request() {
        return new AddressDto(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000,
                AddressType.customer
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
                49000,
                AddressType.customer
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
                49000,
                AddressType.customer
        );
    }

    public static List<Address> addressesForSave = List.of(
            addressForSaveOne(),
            addressForSaveTwo()
    );

    public static Address addressForSave() {
        return new Address(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000,
                AddressType.customer,
                USER_ID
        );
    }

    public static Address addressForSaveOne() {
        return new Address(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000,
                AddressType.customer,
                USER_ID
        );
    }

    public static Address addressForSaveTwo() {
        return new Address(
                null,
                "Ukraina",
                "Kharkiv",
                "Lupokina",
                2,
                null,
                null,
                49000,
                AddressType.delivery,
                USER_ID
        );
    }

    public static List<AddressDto> addressesForEquals = List.of(
            addressForEqualsOne(),
            addressForEqualsTwo()
    );

    public static AddressDto addressForEqualsOne() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000,
                AddressType.customer
        );
    }

    public static AddressDto addressForEqualsTwo() {
        return new AddressDto(
                2L,
                "Ukraina",
                "Kharkiv",
                "Lupokina",
                2,
                null,
                null,
                49000,
                AddressType.delivery
        );
    }

}
