package com.github.master.card.services.impl;

import com.github.master.card.entity.EntityStatus;
import com.github.master.card.entity.Transaction;
import com.github.master.card.repository.TransactionRepo;
import com.github.master.card.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepo transactionRepo;

    @Override
    public Transaction create(Transaction transaction) {
        return this.transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> readAllByStatus(EntityStatus status) {
        return this.transactionRepo.findByStatus(status);
    }

}
