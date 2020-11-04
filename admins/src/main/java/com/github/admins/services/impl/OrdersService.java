package com.github.admins.services.impl;

import com.github.admins.dto.OrderDetailDto;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.IOrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {

    @Override
    public Optional<List<OrderDetailDto>> readAllByStatus(OrderStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderDetailDto>> findByStatusInTime(
            OrderStatus status, String start, String end) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDetailDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(OrderDetailDto o) {

    }

    @Override
    public void update(Long productId, OrderStatus orderStatus) {

    }
}
