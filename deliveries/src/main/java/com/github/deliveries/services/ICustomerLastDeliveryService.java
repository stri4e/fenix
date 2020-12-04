package com.github.deliveries.services;

import com.github.deliveries.entity.CustomerLastDelivery;
import com.github.deliveries.entity.DeliveryType;

import java.math.BigDecimal;
import java.util.UUID;

public interface ICustomerLastDeliveryService {

    CustomerLastDelivery create(CustomerLastDelivery d);

    CustomerLastDelivery readById(Long id);

    CustomerLastDelivery readByUserId(UUID userId);

    void update(Long id, DeliveryType type, String companyName);

    void update(CustomerLastDelivery d);

}
