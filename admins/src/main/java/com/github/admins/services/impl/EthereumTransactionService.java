package com.github.admins.services.impl;

import com.github.admins.dto.EthereumTransactionDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.payload.Receipt;
import com.github.admins.services.IEthereumTransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EthereumTransactionService implements IEthereumTransactionService {

    @Override
    public Optional<EthereumTransactionDto> sendTransaction(Receipt payload) {
        return Optional.empty();
    }

    @Override
    public Optional<EthereumTransactionDto> sendContract(String address, Receipt payload) {
        return Optional.empty();
    }

    @Override
    public Page<EthereumTransactionDto> findAllByStatus(EntityStatus status, Pageable pageable) {
        return null;
    }
}
