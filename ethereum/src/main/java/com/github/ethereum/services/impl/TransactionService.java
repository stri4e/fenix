package com.github.ethereum.services.impl;

import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.entity.Transaction;
import com.github.ethereum.exceptions.NotFound;
import com.github.ethereum.repository.TransactionRepo;
import com.github.ethereum.services.ITransactionService;
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
    public List<Transaction> readAll() {
        return this.transactionRepo.findAll();
    }

    @Override
    public List<Transaction> readAllByStatus(EntityStatus status) {
        return this.transactionRepo.findByStatus(status);
    }

    @Override
    public Transaction readByHash(String hash) {
        return this.transactionRepo.findByHash(hash)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Transaction transaction) {
        this.transactionRepo.save(transaction);
    }
}
