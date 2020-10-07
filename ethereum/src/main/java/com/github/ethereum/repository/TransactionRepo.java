package com.github.ethereum.repository;

import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>,
        PagingAndSortingRepository<Transaction, Long> {

    Optional<Transaction> findByHash(String hash);

    Page<Transaction> findByStatus(EntityStatus status);

}
