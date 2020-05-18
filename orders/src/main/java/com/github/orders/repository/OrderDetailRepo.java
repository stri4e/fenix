package com.github.orders.repository;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepo extends CrudRepository<OrderDetail, Long> {
    Optional<OrderDetail> findByUserId(Long userId);
    List<OrderDetail> findByStatus(OrderStatus status);
    List<OrderDetail> findAllByUserId(Long userId);

    @Modifying
    @Query(value = "UPDATE OrderDetail o SET status =:status WHERE o.id =:id")
    void updateOrderByStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") OrderStatus status
    );

}
