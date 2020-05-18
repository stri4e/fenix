package com.github.products.repository;

import com.github.products.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends PagingAndSortingRepository<Category, Long>, JpaRepository<Category, Long> {

    List<Category> findAll(Sort sort);

    Category findByName(String name);

}
