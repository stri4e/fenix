package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.Transaction;
import com.github.bitcoin.exceptions.NotFound;
import com.github.bitcoin.repository.TransactionRepo;
import com.github.bitcoin.services.ITransactionService;
import lombok.RequiredArgsConstructor;
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
        return this.transactionRepo.findAllByStatus(status);
    }

    @Override
    public Transaction findByHash(String hash) {
        return this.transactionRepo.findByHash(hash)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Transaction transaction) {
        this.transactionRepo.save(transaction);
    }
}
