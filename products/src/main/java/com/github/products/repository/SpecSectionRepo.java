package com.github.products.repository;

import com.github.products.entity.SpecificationSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecSectionRepo extends JpaRepository<SpecificationSection, Long> {

    @Modifying
    @Query(value = "update SpecificationSection ss set ss.title=:title where ss.id=:id")
    void updateTitle(@Param(value = "id") Long id, @Param(value = "title") String title);

}
