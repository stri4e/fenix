package com.github.orders.service;

import com.github.orders.entity.Delivery;
import com.github.orders.entity.DeliveryType;

import java.math.BigDecimal;
import java.util.UUID;

public interface IDeliveryService {

    Delivery create(Delivery d);

    Delivery readById(Long id);

    Delivery readByUserId(UUID userId);

    void update(Long id, DeliveryType type, String companyName, BigDecimal amount);

    void update(Delivery d);

}
