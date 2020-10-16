package com.github.bitcoin.services;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.TrialTransaction;

import java.util.List;

public interface ITrialTransactionService {

    TrialTransaction readByHash(String hash);

    List<TrialTransaction> readByStatus(EntityStatus status);

    TrialTransaction create(TrialTransaction trialTransaction);

    void updateStatus(String hash, EntityStatus status);

}
