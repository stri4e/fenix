package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.*;
import com.github.bitcoin.services.*;
import com.github.wrapper.bitcoin.facade.IFacadeBitcoin;
import com.github.wrapper.bitcoin.model.NewBlock;
import com.github.wrapper.bitcoin.model.TOutput;
import com.github.wrapper.bitcoin.model.TransactionData;
import com.github.wrapper.bitcoin.payload.BlockChainInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.github.bitcoin.utils.TransferObj.toTransaction;
import static com.github.bitcoin.utils.TransferObj.toUnspentOut;

@Service
@Transactional
@RequiredArgsConstructor
public class NetworkService implements INetworkService {

    private final IAccountService accountService;

    private final IUnspentOutService unspentOutService;

    private final IBlockService blockService;

    private final ITransactionService transactionService;

    private final IAddressService addressService;

    private final IFacadeBitcoin facadeBitcoin;

    @Override
    public Long findLastHeight(String url) {
        Block block = this.blockService.readByStatus(EntityStatus.on);
        if (Objects.isNull(block)) {
            BlockChainInfo response = this.facadeBitcoin.fetchInfo(url);
            var number = response.getData().getBlocks().longValue();
            block = this.blockService.create(new Block(number));
        }
        return block.getNumber();
    }

    @Override
    public void handlerBlock(NewBlock block) {
        var height = block.getHeight();
        var blockHash = block.getHash();
        List<TransactionData> transactions = block.getTransactions();
        List<String> addresses = this.addressService.readAllAddresses(EntityStatus.on);
        addresses.forEach(address -> transactions
                .forEach(transaction -> searchTransactions(transaction, address, height, blockHash)));
        this.blockService.update(height);
    }

    private void searchTransactions(TransactionData transaction, String address, long height, String blockHash) {
        incoming(transaction, address, height, blockHash);
        outgoing(transaction, address, height, blockHash);
    }

    private void incoming(TransactionData data, String address, long height, String blockHash) {
        data.getOutputs().stream()
                .filter(output -> address.equals(output.getAddress()))
                .findAny()
                .ifPresent(output -> incoming(data, output, address, height, blockHash));
    }

    private void outgoing(TransactionData data, String address, long height, String blockHash) {
        data.getInputs().stream()
                .filter(input -> address.equals(input.getAddress()))
                .findAny()
                .ifPresent(input -> outgoing(data, height, blockHash));
    }

    private void incoming(TransactionData data, TOutput output, String address, long height, String blockHash) {
        var value = output.getValue();
        var hash = data.getHash();
        Address addr = this.addressService.readByAddress(address);
        Account account = addr.getAccount();
        if (this.transactionService.existByHash(hash)) {
            UnspentOut unspentOut = toUnspentOut(output, hash);
            unspentOut = this.unspentOutService.create(unspentOut);
            account.addAmount(value);
            addr.incoming(unspentOut, value);
        } else {
            Transaction transaction = toTransaction(data, height, blockHash, TransactionType.incoming);
            transaction = this.transactionService.create(transaction);
            account.incoming(transaction, value);
            UnspentOut unspentOut = toUnspentOut(output, hash);
            unspentOut = this.unspentOutService.create(unspentOut);
            addr.incoming(unspentOut, value);
        }
        this.addressService.update(addr);
        this.accountService.update(account);
    }

    private void outgoing(TransactionData data, long height, String blockHash) {
        var hash = data.getHash();
        if (this.transactionService.existByHash(hash)) {
            Transaction transaction = this.transactionService.findByHash(hash);
            transaction.forUpdate(height, blockHash, data.getConfirmation());
            this.transactionService.update(transaction);
        }
    }

}
