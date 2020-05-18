package com.github.products.repository;

import com.github.products.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepo
        extends JpaRepository<Specification, Long> {

}
