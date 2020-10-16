package com.github.bitcoin.repository;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.FeePerKb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeePerKbRepo extends JpaRepository<FeePerKb, Long> {

    Optional<FeePerKb> findByStatus(EntityStatus status);

}
