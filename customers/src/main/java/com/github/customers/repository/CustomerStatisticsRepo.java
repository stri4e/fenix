package com.github.customers.repository;

import com.github.customers.entity.CustomerStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerStatisticsRepo extends JpaRepository<CustomerStatistics, Long> {

    Optional<CustomerStatistics> findByCustomerId(Long customerId);

    @Modifying
    @Query(value = "update CustomerStatistics  cs set cs.totalOrders=:totalOrders where cs.customer.id=:id")
    void updateTotalOrders(
            @Param(value = "id") Long id,
            @Param(value = "totalOrders") Integer totalOrders
    );

    @Modifying
    @Query(value = "update CustomerStatistics  cs set cs.successOrders=:successOrders where cs.customer.id=:id")
    void updateSuccessOrders(
            @Param(value = "id") Long id,
            @Param(value = "successOrders") Integer successOrders
    );

    @Modifying
    @Query(value = "update CustomerStatistics  cs set cs.returnedOrders=:returnedOrders where cs.customer.id=:id")
    void updateReturnedOrders(
            @Param(value = "id") Long id,
            @Param(value = "returnedOrders") Integer returnedOrders
    );

}
