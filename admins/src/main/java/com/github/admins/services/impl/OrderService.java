package com.github.admins.services.impl;

import com.github.admins.dto.OrderDetailDto;
import com.github.admins.payload.OrderDetail;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.IOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Override
    public Optional<List<OrderDetailDto>> readAllByStatus(OrderStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderDetailDto>> findByStatusInTime(
            OrderStatus status, LocalDateTime start, LocalDateTime end) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDetail> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(OrderDetail o) {

    }

    @Override
    public void update(Long productId, OrderStatus orderStatus) {

    }
}
