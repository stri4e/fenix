package com.github.bitcoin.services;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.TrialTransaction;

public interface ITrialTransactionService {

    TrialTransaction readByHash(String hash);

    TrialTransaction create(TrialTransaction trialTransaction);

    void updateStatus(String hash, EntityStatus status);

}
