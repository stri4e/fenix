package com.github.orders.controllers.impl;

import com.github.orders.dto.AddressDto;
import com.github.orders.dto.CustomerDto;
import com.github.orders.entity.Address;
import com.github.orders.entity.AddressType;
import com.github.orders.entity.Customer;

import java.util.UUID;

public class CustomerControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static Customer customerForSave() {
        return new Customer(
                null,
                "Vasia",
                "Utkin",
                "+7832184214",
                USER_ID
        );
    }

    public static CustomerDto customerForEquals() {
        return new CustomerDto(
                1L,
                "Vasia",
                "Utkin",
                "+7832184214",
                address()
        );
    }

    public static CustomerDto customerForUpdate() {
        return new CustomerDto(
                1L,
                "Vasia",
                "Zubkin",
                "+7832184214",
                address()
        );
    }

    public static CustomerDto request() {
        return new CustomerDto(
                1L,
                "Vasia",
                "Utkin",
                "+7832184214",
                address()
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
                49000,
                AddressType.customer,
                USER_ID
        );
    }

    public static AddressDto address() {
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

}
