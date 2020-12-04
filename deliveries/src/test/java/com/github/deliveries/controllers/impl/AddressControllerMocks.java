package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.AddressDto;
import com.github.deliveries.entity.Address;
import com.github.deliveries.entity.CustomerLastDelivery;
import com.github.deliveries.entity.DeliveryType;

import java.math.BigDecimal;
import java.util.UUID;

public class AddressControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static AddressDto request() {
        return new AddressDto(
                null,
                "Ukraina",
                "Dnepropetpovsk obl",
                "Dnepr",
                "Julvern",
                "1",
                "unknown",
                "49000"
        );
    }

    public static AddressDto requestForUpdate() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepropetpovsk obl",
                "Dnepr",
                "Julvern",
                "1",
                "unknown",
                "49000"
        );
    }

    public static AddressDto response() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepropetpovsk obl",
                "Dnepr",
                "Julvern",
                "1",
                "unknown",
                "49000"
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
                "unknown",
                "49000"
        );
    }

    public static AddressDto addressForEquals() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepropetpovsk obl",
                "Dnepr",
                "Julvern",
                "1",
                "unknown",
                "49000"
        );
    }

    public static CustomerLastDelivery deliveryForSave() {
        return new CustomerLastDelivery(
                null,
                DeliveryType.home,
                "Novaposhta",
                USER_ID
        );
    }

}
