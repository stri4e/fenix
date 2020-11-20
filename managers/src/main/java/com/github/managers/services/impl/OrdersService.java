package com.github.managers.services.impl;

import com.github.managers.dto.OrderDetailDto;
import com.github.managers.services.IOrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {

    @Override
    public Page<OrderDetailDto> findStuffOrders(String status, Long staffId, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<OrderDetailDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void assignManager(Long orderId, Long staffId) {

    }

    @Override
    public void update(Long productId, String orderStatus) {

    }
}
