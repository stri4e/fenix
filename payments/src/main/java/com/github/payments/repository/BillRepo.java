package com.github.payments.repository;

import com.github.payments.entity.Bill;
import com.github.payments.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {

    List<Bill> findAllByStatus(EntityStatus status);

    Optional<Bill> findByAddressAndStatus(String address, EntityStatus status);

    @Modifying
    @Query(value = "update Bill b set b.status=:status where b.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
