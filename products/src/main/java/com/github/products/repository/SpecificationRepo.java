package com.github.products.repository;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepo
        extends JpaRepository<Specification, Long> {

    @Modifying
    @Query(value = "update Specification s set s.status=:status where s.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
