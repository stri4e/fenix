package com.github.deliveries.repository;

import com.github.deliveries.entity.CustomerLastDelivery;
import com.github.deliveries.entity.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerLastDeliveryRepo extends JpaRepository<CustomerLastDelivery, Long> {

    Optional<CustomerLastDelivery> findByUserId(UUID userId);

    @Modifying
    @Query(value = "update CustomerLastDelivery d set d.type=:type, d.companyName=:companyName where d.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "type") DeliveryType type,
            @Param(value = "companyName") String companyName
    );

}
