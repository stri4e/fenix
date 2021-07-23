package com.github.products.repository;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {

    Optional<Subcategory> findByName(String name);

    List<Subcategory> findAllByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Subcategory sc set sc.status=:status where sc.id=:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

    @Modifying
    @Query(value = "update Subcategory sc set sc.name=:name where sc.id=:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "name") String name
    );

}
