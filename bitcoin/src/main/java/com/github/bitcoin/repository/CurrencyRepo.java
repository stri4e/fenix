package com.github.bitcoin.repository;

import com.github.bitcoin.entity.Currency;
import com.github.bitcoin.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {

    Optional<Currency> findByStatus(EntityStatus status);

}
