package com.github.products.repository;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepo extends JpaRepository<Criteria, Long> {

    @Modifying
    @Query(value = "update Criteria c set c.status=:status where c.id=:id")
    void updateStatus(@Param(value = "id") Long id,
                      @Param(value = "status") EntityStatus status);

}
