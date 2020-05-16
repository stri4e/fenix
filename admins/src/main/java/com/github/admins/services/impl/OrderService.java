package com.github.admins.services.impl;

import com.github.admins.payload.OrderDetail;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.IOrderService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private static final OrderDetail EMPTY = new OrderDetail();

    @Override
    public List<OrderDetail> readAllByStatus(OrderStatus status) {
        return Collections.emptyList();
    }

    @Override
    public OrderDetail readById(Long id) {
        return EMPTY;
    }

    @Override
    public List<OrderDetail> readByUserId(Long userId) {
        return Collections.emptyList();
    }

    @Override
    public void update(OrderDetail o) {

    }

    @Override
    public void update(Long productId, OrderStatus orderStatus) {

    }
}
