package com.github.payments.repository;

import com.github.payments.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepo extends JpaRepository<Asset, Long> {
}
