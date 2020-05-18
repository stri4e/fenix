package com.github.websocket.services.impl;

import com.github.websocket.payload.OrderDetail;
import com.github.websocket.payload.OrderStatus;
import com.github.websocket.services.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Override
    public List<OrderDetail> readAllByStatus(OrderStatus status) {
        return null;
    }
}
