package com.github.bitcoin.services;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITransactionService {

    Transaction create(Transaction transaction);

    Page<Transaction> readAllByStatus(EntityStatus status);

    Transaction findByHash(String hash);

    void update(Transaction transaction);

    boolean existByHash(String hash);

    void updateConfirmation();

}
