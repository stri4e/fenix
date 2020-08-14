package com.github.orders.service.impl;

import com.github.orders.dto.OrderDto;
import com.github.orders.service.IPushOrders;
import org.springframework.stereotype.Service;

@Service
public class PushOrders implements IPushOrders {
    @Override
    public void pushOrder(OrderDto payload) {
    }
}
