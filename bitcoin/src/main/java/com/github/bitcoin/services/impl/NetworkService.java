package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.*;
import com.github.bitcoin.payload.Report;
import com.github.bitcoin.services.*;
import com.github.facade.bitcoin.IFacadeBitcoin;
import com.github.facade.bitcoin.models.NewBlock;
import com.github.facade.bitcoin.models.TOutput;
import com.github.facade.bitcoin.models.TransactionData;
import com.github.facade.bitcoin.payloads.BlockChainInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Objects;

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

    private final IBillService billService;

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
        block.transactions(
                this::incoming,
                this::outgoing,
                this.addressService::readAllAddresses
        );
        this.blockService.update(block.getHeight());
        this.transactionService.updateConfirmation();
    }

    private void
    incoming(TransactionData data, TOutput output, String address, long height, String blockHash) {
        var value = BigInteger.valueOf(output.getValue());
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
        Report report = this.billService.update(address, value, hash);
        if (report.isNotDifferent()) {
            addr.setStatus(EntityStatus.off);
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
