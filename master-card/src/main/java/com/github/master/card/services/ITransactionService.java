package com.github.master.card.services;

import com.github.master.card.entity.EntityStatus;
import com.github.master.card.entity.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITransactionService {

    Transaction create(Transaction transaction);

    List<Transaction> readAllByStatus(EntityStatus status);

}
