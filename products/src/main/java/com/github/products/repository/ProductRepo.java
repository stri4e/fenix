package com.github.products.repository;

import com.github.products.entity.Product;
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

    Page<Product> findByPublishTrue(Pageable pageable);

    Page<Product> findAllByCategoryName(String name, Pageable pageable);

    @Modifying
    @Query(value = "update Product p set p.publish = :isPublish where p.id = :id")
    void updateIsPublish(
            @Param(value = "isPublish") boolean isPublish,
            @Param(value = "id") Long id);

}
