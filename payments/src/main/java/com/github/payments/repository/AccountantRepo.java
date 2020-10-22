package com.github.payments.repository;

import com.github.payments.entity.Accountant;
import com.github.payments.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountantRepo extends JpaRepository<Accountant, Long> {

    Optional<Accountant> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Accountant a set a.status=:status where a.id=:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
