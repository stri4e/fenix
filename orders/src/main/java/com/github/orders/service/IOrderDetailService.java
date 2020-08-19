package com.github.orders.service;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;

import java.util.List;

public interface IOrderDetailService {

    OrderDetail crete(OrderDetail o);

    OrderDetail readById(Long orderId);

    OrderDetail readUserId(Long userId);

    List<OrderDetail> readAllUserId(Long userId);

    List<OrderDetail> readByStatus(OrderStatus status);

    List<OrderDetail> readByIds(List<Long> ids);

    void update(Long id, OrderStatus status);

    void update(OrderDetail o);

}
