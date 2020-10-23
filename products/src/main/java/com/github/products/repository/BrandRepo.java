package com.github.products.repository;

import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    List<Brand> findAllByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Brand b set b.status=:status where b.id=:id")
    void updateStatus(@Param(value = "id") Long id,
                      @Param(value = "status") EntityStatus status
    );

}
