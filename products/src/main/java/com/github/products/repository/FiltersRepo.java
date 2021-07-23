package com.github.products.repository;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FiltersRepo extends JpaRepository<Filter, Long> {

    @Modifying
    @Query(value = "update Filter f set f.status=:status where f.id=:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

    @Modifying
    @Query(value = "update Filter f set f.title=:#{#flt.title} where f.id=:#{#flt.id}")
    void updateTitle(@Param(value = "flt") Filter flt);

}
