package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.TrialTransaction;
import com.github.bitcoin.exceptions.NotFound;
import com.github.bitcoin.repository.TrialTransactionRepo;
import com.github.bitcoin.services.ITrialTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TrialTransactionService implements ITrialTransactionService {

    private final TrialTransactionRepo trialTransactionRepo;

    @Override
    public TrialTransaction readByHash(String hash) {
        return this.trialTransactionRepo.findByHash(hash)
                .orElseThrow(NotFound::new);
    }

    @Override
    public TrialTransaction create(TrialTransaction trialTransaction) {
        return this.trialTransactionRepo.save(trialTransaction);
    }

    @Override
    public void updateStatus(String hash, EntityStatus status) {
        this.trialTransactionRepo.update(hash, status);
    }
}
