package com.github.ethereum.services;

import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.entity.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITransactionService {

    Transaction create(Transaction transaction);

    List<Transaction> readAll();

    Page<Transaction> readAllByStatus(EntityStatus status);

    Transaction readByHash(String hash);

    void update(Transaction transaction);

}
