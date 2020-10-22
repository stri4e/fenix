package com.github.orders.service;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderDetailService {

    OrderDetail crete(OrderDetail o);

    List<OrderDetail> read(OrderStatus status, LocalDateTime start, LocalDateTime end);

    OrderDetail readById(Long orderId);

    List<OrderDetail> readUserId(Long userId);

    List<OrderDetail> readByStatus(OrderStatus status);

    List<OrderDetail> readByIds(List<Long> ids);

    void update(Long id, OrderStatus status);

    void update(OrderDetail o);

    void updateOrderPaid(Long billId);

}
