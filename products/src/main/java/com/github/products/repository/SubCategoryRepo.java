package com.github.products.repository;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

    Optional<SubCategory> findByName(String name);

    List<SubCategory> findAllByStatus(EntityStatus status);

    List<SubCategory> findAllByStatusAndCategoryName(EntityStatus status, String name);

    @Modifying
    @Query(value = "update SubCategory sc set sc.status=:status where sc.id=:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
