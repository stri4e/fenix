package com.github.admins.services.impl;

import com.github.admins.payload.OrderDetail;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Override
    public Optional<List<OrderDetail>> readAllByStatus(OrderStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDetail> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderDetail>> readByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public void update(OrderDetail o) {

    }

    @Override
    public void update(Long productId, OrderStatus orderStatus) {

    }
}
