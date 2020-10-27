package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.ITransactionController;
import com.github.ethereum.payload.Receipt;
import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.entity.*;
import com.github.ethereum.services.IAccountService;
import com.github.ethereum.services.IContractService;
import com.github.ethereum.services.IFeeService;
import com.github.ethereum.services.ITransactionService;
import com.github.ethereum.utils.Logging;
import com.github.ethereum.utils.TransferObj;
import com.github.facade.ethrereum.IFacadeEthereum;
import com.github.facade.ethrereum.model.KeyPair;
import com.github.facade.ethrereum.model.TransactionData;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.github.ethereum.entity.Contract.DEFAULT_CONTRACT_NAME;
import static com.github.ethereum.utils.TransferObj.fromTransaction;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/transactions")
public class TransactionController implements ITransactionController {

    private final IFacadeEthereum facade;

    private final IAccountService accountService;

    private final ITransactionService transactionService;

    private final IContractService contractService;

    private final IFeeService feeService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public TransactionDto sendTransaction(Receipt payload) {
        Contract contract = this.contractService.readByName(DEFAULT_CONTRACT_NAME);
        Account account = this.accountService.readByAddress(payload.getFrom());
        Fee fee = this.feeService.readByStatus(EntityStatus.on);
        KeyPair keys = new KeyPair(
                account.getPrivateKey(), account.getPublicKey(), account.getAddress()
        );
        TransactionData response = this.facade.send(
                keys, fee.getGasPrice(), payload.getTo(),
                payload.getValue(), fee.getFee()
        );
        Transaction transaction = this.transactionService.create(
                TransferObj.toOutgoingTransaction(response, contract)
        );
        account.addTransaction(transaction);
        this.accountService.update(account);
        return fromTransaction(transaction);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public TransactionDto sendContract(String address, Receipt payload) {
        Contract contract = this.contractService.readByName(address);
        Account account = this.accountService.readByAddress(payload.getFrom());
        Fee fee = this.feeService.readByStatus(EntityStatus.on);
        KeyPair keys = new KeyPair(
                account.getPrivateKey(), account.getPublicKey(), account.getAddress()
        );
        TransactionData response = this.facade.sendERC20Token(
                keys, address, fee.getGasPrice(), payload.getTo(),
                payload.getValue(), fee.getFee()
        );
        Transaction transaction = this.transactionService.create(
                TransferObj.toOutgoingTransaction(response, contract)
        );
        account.addTransaction(transaction);
        this.accountService.update(account);
        return fromTransaction(transaction);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<TransactionDto> findAllByStatus(EntityStatus status, Pageable pageable) {
        Page<Transaction> transactions = this.transactionService.readAllByStatus(status);
        return new PageImpl<>(
                transactions.stream()
                        .map(TransferObj::fromTransaction)
                        .collect(Collectors.toList()),
                pageable, transactions.getTotalElements()
        );
    }

}
