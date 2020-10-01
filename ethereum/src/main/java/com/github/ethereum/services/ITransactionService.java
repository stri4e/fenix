package com.github.ethereum.services;

import com.github.ethereum.entity.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction create(Transaction transaction);

    List<Transaction> readAll();

    Transaction readByHash(String hash);

    void update(Transaction transaction);

}
