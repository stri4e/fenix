package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.AddressDto;
import com.github.deliveries.dto.DeliveryDto;
import com.github.deliveries.entity.Address;
import com.github.deliveries.entity.Delivery;
import com.github.deliveries.entity.DeliveryType;

import java.math.BigDecimal;
import java.util.UUID;

public class DeliveryControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static Delivery deliveryForSave() {
        return new Delivery(
                null,
                DeliveryType.home,
                "Novaposhta",
                BigDecimal.TEN,
                USER_ID
        );
    }

    public static DeliveryDto deliveryForEquals() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Novaposhta",
                address(),
                new BigDecimal("10.0000")
        );
    }

    public static DeliveryDto deliveryForEquals2() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Novaposhta",
                new AddressDto(),
                new BigDecimal("10.0000")
        );
    }

    public static DeliveryDto request() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Novaposhta",
                address(),
                new BigDecimal("10.0000")
        );
    }

    public static DeliveryDto deliveryForUpdate() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Misexpres",
                address(),
                new BigDecimal("10.0000")
        );
    }

    public static Address addressForSave() {
        return new Address(
                null,
                "Ukraina",
                "Dnepropetpovsk obl",
                "Dnepr",
                "Julvern",
                "1",
                null,
                "49000"
        );
    }

    public static AddressDto address() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepropetpovsk obl",
                "Dnepr",
                "Julvern",
                "1",
                null,
                "49000"
        );
    }

}
