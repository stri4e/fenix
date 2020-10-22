package com.github.orders.repository;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepo extends
        PagingAndSortingRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail> {

    List<OrderDetail> findByUserId(Long userId);

    List<OrderDetail> findByStatus(OrderStatus status);

    List<OrderDetail> findByStatusAndCreateAtBetween(
            OrderStatus status, LocalDateTime start, LocalDateTime end);

    @Modifying
    @Query(value = "UPDATE OrderDetail o SET o.status =:status WHERE o.id =:id")
    void updateOrderByStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") OrderStatus status
    );

    @Modifying
    @Query(value = "UPDATE OrderDetail o SET o.status =:status WHERE o.billId =:billId")
    void updateOrderPaid(
            @Param(value = "billId") Long billId,
            @Param(value = "status") OrderStatus status
    );

}
