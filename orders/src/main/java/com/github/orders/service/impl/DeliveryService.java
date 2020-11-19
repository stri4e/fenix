package com.github.orders.service.impl;

import com.github.orders.dto.DeliveryDto;
import com.github.orders.service.IDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryService implements IDeliveryService {

    @Override
    public Optional<DeliveryDto> readById(Long id) {
        return Optional.empty();
    }
}
