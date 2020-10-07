package com.github.ethereum.services.impl;

import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.entity.Transaction;
import com.github.ethereum.exceptions.NotFound;
import com.github.ethereum.repository.TransactionRepo;
import com.github.ethereum.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"transaction", "transactions"})
public class TransactionService implements ITransactionService {

    private final TransactionRepo transactionRepo;

    @Override
    @Caching(
            put = @CachePut(value = "transaction", key = "#transaction.id"),
            evict = @CacheEvict(value = "transactions", allEntries = true)
    )
    public Transaction create(Transaction transaction) {
        return this.transactionRepo.save(transaction);
    }

    @Override
    @Cacheable(value = "transactions", unless = "#result.size() == 0")
    public List<Transaction> readAll() {
        return this.transactionRepo.findAll();
    }

    @Override
    @Cacheable(value = "transactions", unless = "#result.size() == 0", key = "#status")
    public Page<Transaction> readAllByStatus(EntityStatus status) {
        return this.transactionRepo.findByStatus(status);
    }

    @Override
    @Cacheable(value = "transaction", key = "#hash")
    public Transaction readByHash(String hash) {
        return this.transactionRepo.findByHash(hash)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Caching(
            put = @CachePut(value = "transaction", key = "#transaction.id"),
            evict = @CacheEvict(value = "transactions", allEntries = true)
    )
    public void update(Transaction transaction) {
        this.transactionRepo.save(transaction);
    }

}
