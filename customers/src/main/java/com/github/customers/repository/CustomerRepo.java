package com.github.customers.repository;

import com.github.customers.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    Optional<Customer> findByUserId(UUID userId);

    @Modifying
    @Query(value = "update Customer c set c.customerName=:customerName, " +
            " c.customerEmail=:customerEmail, c.customerPhone=:customerPhone where c.id=:id")
    void update(
           @Param(value = "id") Long id,
           @Param(value = "customerName") String customerName,
           @Param(value = "customerEmail") String customerEmail,
           @Param(value = "customerPhone") String customerPhone
    );

}
