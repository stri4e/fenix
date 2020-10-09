package com.github.orders.repository;

import com.github.orders.entity.Branch;
import com.github.orders.entity.BranchStatus;
import com.github.orders.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {

    @Modifying
    @Query(value = "UPDATE Branch b SET b.status =:status WHERE b.id =:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
