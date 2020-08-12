package com.github.products.repository;

import com.github.products.entity.Product;
import com.github.products.entity.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

    Page<Product> findAllByCategoryName(String name, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Product p SET p.status =:status WHERE p.id =:id")
    void updateStatus(
            @Param(value = "status") ProductStatus status,
            @Param(value = "id") Long id);

}
