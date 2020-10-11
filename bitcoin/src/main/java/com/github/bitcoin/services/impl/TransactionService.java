package com.github.bitcoin.services.impl;

import com.github.bitcoin.repository.TransactionRepo;
import com.github.bitcoin.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepo transactionRepo;

}
