package com.github.payments.repository;

import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.Alias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AliasRepo extends JpaRepository<Alias, Long> {

    Optional<Alias> findByBill_Id(Long billId);

    @Modifying
    @Query(value = "update Alias a set a.status=:status where a.id=:id")
    void updateStatus(@Param(value = "id") Long id,
                      @Param(value = "status") EntityStatus status);

}
