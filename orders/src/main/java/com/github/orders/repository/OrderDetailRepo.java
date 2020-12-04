package com.github.orders.repository;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderDetailRepo extends
        PagingAndSortingRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail> {

    Page<OrderDetail> findByUserId(UUID userId, Pageable pageable);

    Page<OrderDetail> findByCustomerId(Long customerId, Pageable pageable);

    Page<OrderDetail> findByStatus(OrderStatus status, Pageable pageable);

    Page<OrderDetail> findByStaffIdNull(Pageable pageable);

    Page<OrderDetail> findByStaffIdAndStatus(Long staffId, OrderStatus status, Pageable pageable);

    Optional<Integer> countAllByCustomerId(Long customerId);

    Optional<Integer> countAllByCustomerIdAndStatus(Long customerId, OrderStatus status);

    @Query(value = "select od.customerId from OrderDetail od where od.id=:orderId")
    Optional<Long> findCustomerIdByOrderId(@Param(value = "orderId") Long orderId);

    @Modifying
    @Query(value = "update OrderDetail o set o.status =:status where o.id =:id")
    void updateOrderByStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") OrderStatus status
    );

    @Modifying
    @Query(value = "update OrderDetail o set o.staffId=:staffId where o.id =:id")
    void updateManagerOrder(
            @Param(value = "id") Long id,
            @Param(value = "staffId") Long staffId
    );

}
