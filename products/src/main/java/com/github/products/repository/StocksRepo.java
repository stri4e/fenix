package com.github.products.repository;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StocksRepo extends JpaRepository<Stock, Long> {

    List<Stock> findByStatus(EntityStatus status);

    Optional<Stock> findByNameAndNumber(String name, String number);

    @Modifying
    @Query(value = "update Stock s set s.status=:status where s.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
