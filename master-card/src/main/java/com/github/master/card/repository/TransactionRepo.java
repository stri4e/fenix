package com.github.master.card.repository;

import com.github.master.card.entity.EntityStatus;
import com.github.master.card.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>,
        PagingAndSortingRepository<Transaction, Long> {

    List<Transaction> findByStatus(EntityStatus status);

}
