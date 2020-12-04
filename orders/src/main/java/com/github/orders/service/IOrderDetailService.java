package com.github.orders.service;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IOrderDetailService {

    OrderDetail crete(OrderDetail o);

    OrderDetail readById(Long orderId);

    Long readCustomerIdByOrderId(Long orderId);

    Page<OrderDetail> readUserId(UUID userId, Pageable pageable);

    Page<OrderDetail> readByCustomerId(Long customerId, Pageable pageable);

    Page<OrderDetail> readByStatus(OrderStatus status, Pageable pageable);

    Page<OrderDetail> readByStaffIdNull(Pageable pageable);

    Page<OrderDetail> readByStaffIdAndStatus(Long staffId, OrderStatus status, Pageable pageable);

    Integer countTotalByCustomerId(Long customerId);

    Integer countSuccessByCustomerId(Long customerId);

    Integer countReturnedByCustomerId(Long customerId);

    void update(Long id, OrderStatus status);

    void updateOrderManager(Long orderId, Long staffId);

}
