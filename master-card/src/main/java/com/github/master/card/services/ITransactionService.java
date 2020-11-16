package com.github.master.card.services;

import com.github.master.card.entity.EntityStatus;
import com.github.master.card.entity.Transaction;
import org.springframework.data.domain.Page;

public interface ITransactionService {

    Transaction create(Transaction transaction);

    Page<Transaction> readAllByStatus(EntityStatus status);

}
