package com.github.orders.repository;

import com.github.orders.entity.Delivery;
import com.github.orders.entity.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findByUserId(UUID userId);

    @Modifying
    @Query(value = "update Delivery d set d.type=:type, d.companyName=:companyName," +
            " d.address=:address, d.amount=:amount where d.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "type") DeliveryType type,
            @Param(value = "companyName") String companyName,
            @Param(value = "address") String address,
            @Param(value = "amount") BigDecimal amount
    );

}
