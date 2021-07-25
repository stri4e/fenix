package com.github.products.repository;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    Page<Product> findByStatus(EntityStatus status, Pageable pageable);

    Page<Product> findAllBySubcategoryName(String name, Pageable pageable);

    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    @Modifying
    @Query(value = "update Product p set p.status =:status where p.id =:id")
    void updateStatus(
            @Param(value = "status") EntityStatus status,
            @Param(value = "id") Long id
    );

    @Modifying
    @Query(value = "update Product p set p.name =:#{#product.name}, p.price=:#{#product.price}, p.description=:#{#product.description}," +
            "p.previewImage=:#{#product.previewImage} where p.id =:#{#product.id}")
    void updateProduct(@Param(value = "product") Product product);

    @Modifying
    @Query(value = "update Product p set p.boughtCount = p.boughtCount + 1 where p.id =:id")
    void updateBoughCountPlus(@Param(value = "id") Long id);

    @Modifying
    @Query(value = "update Product p set p.boughtCount = p.boughtCount - 1 where p.id =:id")
    void updateBoughCountMinus(@Param(value = "id") Long id);

}
