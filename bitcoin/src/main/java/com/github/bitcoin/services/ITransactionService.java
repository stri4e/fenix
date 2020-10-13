package com.github.bitcoin.services;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction create(Transaction transaction);

    List<Transaction> readAllByStatus(EntityStatus status);

    Transaction findByHash(String hash);

    void update(Transaction transaction);

}
