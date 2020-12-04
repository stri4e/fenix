package com.github.accounts.repository;

import com.github.accounts.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewsRepo extends JpaRepository<View, Long> {

    boolean existsByProductId(Long productId);

    View findByProductId(Long productId);

}
