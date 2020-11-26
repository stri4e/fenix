package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.AddressDto;
import com.github.deliveries.dto.CustomerLastDeliveryDto;
import com.github.deliveries.entity.Address;
import com.github.deliveries.entity.CustomerLastDelivery;
import com.github.deliveries.entity.DeliveryType;

import java.math.BigDecimal;
import java.util.UUID;

public class CustomerLastDeliveryControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static CustomerLastDelivery deliveryForSave() {
        return new CustomerLastDelivery(
                null,
                DeliveryType.home,
                "Novaposhta",
                USER_ID
        );
    }

    public static CustomerLastDeliveryDto deliveryForEquals() {
        return new CustomerLastDeliveryDto(
                1L,
                DeliveryType.home,
                "Novaposhta",
                address()
        );
    }

    public static CustomerLastDeliveryDto deliveryForEquals2() {
        return new CustomerLastDeliveryDto(
                1L,
                DeliveryType.home,
                "Novaposhta",
                new AddressDto()
        );
    }

    public static CustomerLastDeliveryDto request() {
        return new CustomerLastDeliveryDto(
                1L,
                DeliveryType.home,
                "Novaposhta",
                address()
        );
    }

    public static CustomerLastDeliveryDto deliveryForUpdate() {
        return new CustomerLastDeliveryDto(
                1L,
                DeliveryType.home,
                "Misexpres",
                address()
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
