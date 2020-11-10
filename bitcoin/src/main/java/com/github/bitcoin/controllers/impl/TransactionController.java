package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.ITransactionController;
import com.github.bitcoin.payload.Receipt;
import com.github.bitcoin.dto.TransactionDto;
import com.github.bitcoin.dto.TrialTransactionDto;
import com.github.bitcoin.entity.*;
import com.github.bitcoin.exceptions.SendTransactionFailed;
import com.github.bitcoin.services.*;
import com.github.bitcoin.utils.Logging;
import com.github.bitcoin.utils.TransferObj;
import com.github.facade.bitcoin.IFacadeBitcoin;
import com.github.facade.bitcoin.models.ResponseTrx;
import com.github.facade.bitcoin.models.UnspentOutput;
import com.github.facade.bitcoin.transaction.Claim;
import com.github.facade.bitcoin.transaction.NewTransaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.github.bitcoin.utils.TransferObj.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/transactions")
public class TransactionController implements ITransactionController {

    private final IFacadeBitcoin facadeBitcoin;

    private final ITransactionService transactionService;

    private final ITrialTransactionService trialTransactionService;

    private final IAddressService addressService;

    private final IAccountService accountService;

    private final IUnspentOutService unspentOutService;

    private final IFeePerKbService feePerKbService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<TrialTransactionDto> findTrialByStatus(EntityStatus status) {
        return this.trialTransactionService.readByStatus(status).stream()
                .map(TransferObj::fromTrialTransaction)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public TrialTransactionDto generateTransaction(Claim claim, Receipt payload) {
        FeePerKb feePerKb = this.feePerKbService.readActual();
        Address address = this.addressService.readByAddress(payload.getFrom());
        Account account = address.getAccount();
        List<UnspentOut> outs = address.getOuts();
        List<UnspentOutput> unspentOutputs = outs.stream()
                .map(o -> formOut(address.getAddress(), o, Boolean.FALSE))
                .collect(Collectors.toList());
        NewTransaction transaction = transaction(
                account, address, payload, new BigDecimal(feePerKb.getFee()),
                unspentOutputs, this.facadeBitcoin.getNetwork(),
                this.facadeBitcoin.getDerivation(), claim
        );
        List<UnspentOut> spentOuts = unspentOutputs.stream()
                .map(o -> findByIndexAndHash(o.getIndex(), o.getTxHash(), outs))
                .filter(Objects::nonNull)
                .map(o -> this.unspentOutService.update(o, EntityStatus.half_off))
                .collect(Collectors.toList());
        return fromTrialTransaction(this.trialTransactionService.create(
                trial(transaction, payload, spentOuts)
        ));
    }

    private UnspentOut findByIndexAndHash(Integer index, String hash, List<UnspentOut> outs) {
        Predicate<UnspentOut> findIndex = o -> index.equals(o.getIndex());
        Predicate<UnspentOut> findHash = o -> hash.equals(o.getTrxHash());
        return outs.stream().filter(o -> findIndex.and(findHash).test(o))
                .findFirst().orElse(null);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void sendTransaction(TrialTransactionDto payload) {
        var hash = payload.getHash();
        TrialTransaction trx = this.trialTransactionService.readByHash(hash);
        ResponseTrx response = this.facadeBitcoin.send(trx.getSignedTrx());
        if (response.isHasError()) {
            trx.getUnspentOuts().forEach(o -> this.unspentOutService.update(o, EntityStatus.off));
            throw new SendTransactionFailed(response.getError().getMessage());
        } else {
            Address address = this.addressService.readByAddress(payload.getFrom());
            Account account = address.getAccount();
            this.trialTransactionService.updateStatus(hash, EntityStatus.off);
            Transaction transaction = doTransaction(trx);
            this.transactionService.create(transaction);
            updateAmount(trx.getValue(), trx.getChange(), address, account);
        }
    }

    private Transaction doTransaction(TrialTransaction trx) {
        var isZero = trx.getChange().equals(BigInteger.ZERO);
        List<String> outputs = isZero ? List.of(trx.getTo()) : List.of(trx.getTo(), trx.getFrom());
        return new Transaction(
                trx.getHash(),
                trx.getValue(),
                List.of(trx.getFrom()),
                outputs,
                TransactionType.outgoing
        );
    }

    private void updateAmount(BigInteger value, BigInteger change, Address address, Account account) {
        address.outgoing(value, change);
        account.outgoing(value, change);
        this.addressService.update(address);
        this.accountService.update(account);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void canceledTransaction(@Valid TrialTransactionDto payload) {
        var hash = payload.getHash();
        TrialTransaction trx = this.trialTransactionService.readByHash(payload.getHash());
        trx.getUnspentOuts().forEach(o -> this.unspentOutService.update(o, EntityStatus.on));
        this.trialTransactionService.updateStatus(hash, EntityStatus.off);
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
