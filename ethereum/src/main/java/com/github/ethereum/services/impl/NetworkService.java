package com.github.ethereum.services.impl;

import com.github.ethereum.entity.*;
import com.github.ethereum.payload.Report;
import com.github.ethereum.services.*;
import com.github.ethereum.utils.Logging;
import com.github.facade.ethrereum.IFacadeEthereum;
import com.github.facade.ethrereum.model.Information;
import com.github.facade.ethrereum.model.TransactionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.github.ethereum.entity.Contract.DEFAULT_CONTRACT_NAME;
import static com.github.ethereum.utils.TransferObj.toIncomingTransaction;
import static com.github.ethereum.entity.EntityStatus.*;

@Service
@RequiredArgsConstructor
public class NetworkService implements INetworkService {

    private final IFacadeEthereum facade;

    private final IAccountService accountService;

    private final IContractService contractService;

    private final ITransactionService transactionService;

    private final IFeeService feeService;

    private final IBlockService blockService;

    private final IBillService billService;

    @Override
    @Logging
    public Long findBlockNumber() {
        Block block = this.blockService.readByStatus(on);
        if(Objects.isNull(block)) {
            return this.facade.bastBlock()
                    .orElseGet(() -> this.blockService.createDefault().getNumber());
        }
        return block.getNumber();
    }

    @Override
    @Logging
    public void createOrUpdateFee(Information info) {
        Fee fee = this.feeService.readByStatus(on);
        if (Objects.isNull(fee)) {
            this.feeService.create(new Fee(info.getFee(), info.getGasPrice(), on));
        } else {
            this.feeService.update(info.getFee(), info.getGasPrice(), on);
        }
    }

    @Override
    public List<String> contractsAddresses() {
        return this.contractService.readAllAddresses();
    }

    @Override
    public List<String> accountAddresses() {
        return this.accountService.readAddressesByStatus(on);
    }

    @Override
    @Logging
    public void incomingTransaction(TransactionData data) {
        if (Objects.nonNull(data)) {
            var address = data.getTo();
            var value = data.getValue();
            Account account = this.accountService.readByAddress(address);
            Contract contract = this.contractService.readByName(DEFAULT_CONTRACT_NAME);
            Transaction tmp = toIncomingTransaction(data, contract);
            Transaction t = this.transactionService.create(tmp);
            Report report = this.billService.update(address, value, t.getHash());
            if (report.isNotDifferent()) {
                account.forUpdateCurrency(value, t, off);
            } else {
                account.forUpdateCurrency(value, t);
            }
            this.accountService.update(account);
        }
    }

    @Override
    @Logging
    public void outgoingTransaction(TransactionData data) {
        if (Objects.nonNull(data)) {
            Account account = this.accountService.readByAddress(data.getFrom());
            account.subCurrencyBal(data.getValue(), data.getFee());
            this.accountService.update(account);
            Transaction t = this.transactionService.readByHash(data.getHash());
            t.forUpdate(data.getBlockHash(), data.getBlockNumber(), on);
            this.transactionService.update(t);
        }
    }

    @Override
    @Logging
    public void incomingContract(TransactionData data) {
        if (Objects.nonNull(data)) {
            var address = data.getTo();
            var value = data.getValue();
            Account account = this.accountService.readByAddress(address);
            Contract contract = this.contractService.readByAddress(data.getContact());
            Transaction tmp = toIncomingTransaction(data, contract);
            Transaction t = this.transactionService.create(tmp);
            Report report = this.billService.update(address, value, t.getHash());
            if (report.isNotDifferent()) {
                account.forUpdateContract(contract.getName(), value, t, off);
            } else {
                account.forUpdateContract(contract.getName(), value, t);
            }
            this.accountService.update(account);
        }
    }

    @Override
    @Logging
    public void outgoingContract(TransactionData data) {
        if (Objects.nonNull(data)) {
            Contract contract = this.contractService.readByAddress(data.getContact());
            Account account = this.accountService.readByAddress(data.getFrom());
            account.subContractBal(contract.getName(), data.getValue(), data.getFee());
            this.accountService.update(account);
            Transaction t = this.transactionService.readByHash(data.getHash());
            t.forUpdate(data.getBlockHash(), data.getBlockNumber(), on);
            this.transactionService.update(t);
        }
    }

    @Override
    @Logging
    public void updateBlockNumber(Long number) {
        this.blockService.update(number, on);
    }

}
