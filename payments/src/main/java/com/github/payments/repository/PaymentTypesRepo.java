package com.github.payments.repository;

import com.github.payments.entity.PaymentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypesRepo extends JpaRepository<PaymentTypes, Long> {
}
