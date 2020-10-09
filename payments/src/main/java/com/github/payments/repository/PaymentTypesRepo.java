package com.github.payments.repository;

import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.PaymentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentTypesRepo extends JpaRepository<PaymentTypes, Long> {

    Optional<PaymentTypes> findByAlias(String alias);

    List<PaymentTypes> findAllByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update PaymentTypes pt set pt.status=:status where pt.id=:id")
    void update(@Param(value = "id") Long id,
                @Param(value = "status") EntityStatus status);

}
