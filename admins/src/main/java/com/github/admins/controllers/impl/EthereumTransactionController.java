package com.github.admins.controllers.impl;

import com.github.admins.controllers.IEthereumTransactionController;
import com.github.admins.dto.EthereumTransactionDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.payload.EntityStatus;
import com.github.admins.payload.Receipt;
import com.github.admins.services.IEthereumTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/ethereum/transactions")
public class EthereumTransactionController implements IEthereumTransactionController {

    private final IEthereumTransactionService ethereumTransactionService;

    @Override
    public EthereumTransactionDto sendTransaction(Receipt payload) {
        return this.ethereumTransactionService.sendTransaction(payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    public EthereumTransactionDto sendContract(String address, Receipt payload) {
        return this.ethereumTransactionService.sendContract(address, payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    public Page<EthereumTransactionDto> findAllByStatus(EntityStatus status, Pageable pageable) {
        return this.ethereumTransactionService.findAllByStatus(status, pageable);
    }
}
