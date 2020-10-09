package com.github.orders.repository;

import com.github.orders.entity.Company;
import com.github.orders.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    List<Company> findAllByStatus(EntityStatus status);

    @Modifying
    @Query(value = "UPDATE Company c SET c.status =:status WHERE c.id =:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
