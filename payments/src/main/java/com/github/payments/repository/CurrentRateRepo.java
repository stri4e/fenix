package com.github.payments.repository;

import com.github.payments.entity.CurrentRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentRateRepo extends JpaRepository<CurrentRate, Long> {
}
