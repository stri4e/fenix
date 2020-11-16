package com.github.orders.service.impl;

import com.github.orders.entity.Delivery;
import com.github.orders.entity.DeliveryType;
import com.github.orders.exceptions.NotFound;
import com.github.orders.repository.DeliveryRepo;
import com.github.orders.service.IDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryService implements IDeliveryService {

    private final DeliveryRepo deliveryRepo;

    @Override
    public Delivery create(Delivery d) {
        return this.deliveryRepo.save(d);
    }

    @Override
    public Delivery readById(Long id) {
        return this.deliveryRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Delivery readByUserId(UUID userId) {
        return this.deliveryRepo.findByUserId(userId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, DeliveryType type, String companyName, BigDecimal amount) {
        this.deliveryRepo.update(id, type, companyName, amount);
    }

    @Override
    public void update(Delivery d) {
        this.deliveryRepo.save(d);
    }

}
