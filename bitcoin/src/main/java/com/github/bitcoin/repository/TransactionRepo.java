package com.github.bitcoin.repository;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>,
        PagingAndSortingRepository<Transaction, Long> {

    Optional<Transaction> findByHash(String hash);

    Page<Transaction> findAllByStatus(EntityStatus status);

    boolean existsByHash(String hash);

    @Modifying
    @Query(value = "update Transaction t set t.confirmations = count(t.confirmations) where t.confirmations < 6")
    void updateConfirmation();

}
