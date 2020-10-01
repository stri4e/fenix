package com.github.ethereum.repository;

import com.github.ethereum.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByHash(String hash);

}
