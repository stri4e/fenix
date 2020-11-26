package com.github.deliveries.services.impl;

import com.github.deliveries.entity.CustomerLastDelivery;
import com.github.deliveries.entity.DeliveryType;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.CustomerLastDeliveryRepo;
import com.github.deliveries.services.ICustomerLastDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerLastDeliveryService implements ICustomerLastDeliveryService {

    private final CustomerLastDeliveryRepo deliveryRepo;

    @Override
    public CustomerLastDelivery create(CustomerLastDelivery d) {
        return this.deliveryRepo.save(d);
    }

    @Override
    public CustomerLastDelivery readById(Long id) {
        return this.deliveryRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public CustomerLastDelivery readByUserId(UUID userId) {
        return this.deliveryRepo.findByUserId(userId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, DeliveryType type, String companyName) {
        this.deliveryRepo.update(id, type, companyName);
    }

    @Override
    public void update(CustomerLastDelivery d) {
        this.deliveryRepo.save(d);
    }

}
