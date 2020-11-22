package com.github.orders.service;

import com.github.orders.entity.OrderItem;

import java.util.List;

public interface IOrderItemsService {
    List<OrderItem> createAll(List<OrderItem> items);
}
