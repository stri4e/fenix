package com.github.orders.service;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IOrderDetailService {

    OrderDetail crete(OrderDetail o);

    List<OrderDetail> read(OrderStatus status, LocalDateTime start, LocalDateTime end);

    OrderDetail readById(Long orderId);

    List<OrderDetail> readUserId(UUID userId);

    List<OrderDetail> readByStatus(OrderStatus status);

    List<OrderDetail> readByIds(List<Long> ids);

    void update(Long id, OrderStatus status);

    void update(OrderDetail o);

    void updateOrderPaid(Long billId);

}
