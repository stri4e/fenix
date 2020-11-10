package com.github.ethereum.repository;

import com.github.ethereum.entity.Currency;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {

    Optional<Currency> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Currency c set c.name=:name, c.fullName=:fullName, c.addressRegex=:addressRegex, c.pow=:pow where c.id=:id")
    void update(Long id, String name, String fullName, String addressRegex, Integer pow);

}
