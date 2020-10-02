package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.ITransactionController;
import com.github.ethereum.dto.ReceiptDto;
import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.entity.*;
import com.github.ethereum.services.IAccountService;
import com.github.ethereum.services.IContractService;
import com.github.ethereum.services.IFeeService;
import com.github.ethereum.services.ITransactionService;
import com.github.ethereum.utils.TransferObj;
import com.github.wrapper.ethrereum.facade.IFacadeEthereum;
import com.github.wrapper.ethrereum.model.KeyPair;
import com.github.wrapper.ethrereum.model.TransactionData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.ethereum.entity.Contract.DEFAULT_CONTRACT_NAME;

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
    public void sendTransaction(ReceiptDto payload) {
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
    }

    @Override
    public void sendContract(String address, ReceiptDto payload) {
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
    }

    @Override
    public List<TransactionDto> findAllByStatus(EntityStatus status) {
        return this.transactionService.readAllByStatus(status)
                .stream().map(TransferObj::fromTransaction)
                .collect(Collectors.toList());
    }

}
