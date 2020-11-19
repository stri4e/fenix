package com.github.orders.service;

import com.github.orders.dto.DeliveryDto;

import java.util.Optional;

public interface IDeliveryService {

    Optional<DeliveryDto> readById(Long id);

}
