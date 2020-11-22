package com.github.customers.controllers.impl;

import com.github.customers.dto.AddressDto;
import com.github.customers.entity.Address;
import com.github.customers.entity.Customer;

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
                "1",
                null,
                null,
                "49000"
        );
    }

    public static AddressDto requestForUpdate() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnipropetrovsc",
                "Julvern",
                "1",
                null,
                null,
                "49000"
        );
    }

    public static AddressDto response() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepr",
                "Julvern",
                "1",
                null,
                null,
                "49000"
        );
    }

    public static Address addressForSave() {
        return new Address(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                "1",
                null,
                null,
                "49000"
        );
    }

    public static AddressDto addressForEquals() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepr",
                "Julvern",
                "1",
                null,
                null,
                "49000"
        );
    }

    public static Customer customerForSave() {
        return new Customer(
                null,
                "Vasia",
                "Utkin",
                "+7832184214",
                USER_ID
        );
    }

}
