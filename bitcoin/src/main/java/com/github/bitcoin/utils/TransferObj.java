package com.github.bitcoin.utils;

import com.github.bitcoin.dto.*;
import com.github.bitcoin.entity.*;
import com.github.bitcoin.payload.Receipt;
import com.github.wrapper.bitcoin.facade.IFacadeBitcoin;
import com.github.wrapper.bitcoin.model.*;
import com.github.wrapper.bitcoin.transaction.NewTransaction;
import com.github.wrapper.bitcoin.utils.Network;
import com.github.wrapper.bitcoin.utils.WrapUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class TransferObj {

    public static AccountDto fromAccount(Account data) {
        return new AccountDto(
                data.getId(),
                data.getAmount(),
                data.getAddresses().stream()
                        .map(TransferObj::fromAddress)
                .collect(Collectors.toList()),
                data.getTransactions().stream()
                .map(TransferObj::fromTransaction)
                .collect(Collectors.toList())
        );
    }

    public static TransactionDto fromTransaction(Transaction data) {
        return new TransactionDto(
                data.getId(),
                data.getBlockHeight(),
                data.getBlockHash(),
                data.getHash(),
                data.getConfirmations(),
                data.getValue(),
                data.getInputs(),
                data.getOutputs()
        );
    }

    public static TrialTransactionDto fromTrialTransaction(TrialTransaction data) {
        return new TrialTransactionDto(
                data.getHash(),
                data.getValue(),
                data.getFee(),
                data.getChange(),
                data.getFrom(),
                data.getTo()
        );
    }

    public static Currency toCurrency(CurrencyDto data) {
        return new Currency(
                data.getId(),
                data.getName(),
                data.getFullName(),
                data.getAddressRegex(),
                data.getPow()
        );
    }

    public static CurrencyDto fromCurrency(Currency data) {
        return new CurrencyDto(
                data.getId(),
                data.getName(),
                data.getFullName(),
                data.getAddressRegex(),
                data.getPow()
        );
    }

    public static Address toAddress(AddressDto data) {
        return new Address(
                data.getId(),
                data.getIndex(),
                data.getAddress(),
                data.getAmount()
        );
    }

    public static AddressDto fromAddress(Address data) {
        return new AddressDto(
                data.getId(),
                data.getIndex(),
                data.getAddress(),
                data.getAmount()
        );
    }

    public static Transaction toTransaction(TransactionData data, long blockHeight, String blockHash) {
        return new Transaction(
                blockHeight,
                blockHash,
                data.getHash(),
                data.getConfirmation(),
                data.getInputs().stream()
                        .map(TInput::getAddress)
                        .collect(Collectors.toList()),
                data.getOutputs().stream()
                        .map(TOutput::getAddress)
                        .collect(Collectors.toList())
        );
    }

    public static UnspentOut toUnspentOut(TOutput data, String hash) {
        return new UnspentOut(
                data.getIndex(),
                data.getPubKeyHash(),
                data.getScript(),
                BigInteger.valueOf(data.getValue()),
                hash
        );
    }

    public static UnspentOutput formOut(String address, UnspentOut o, boolean isChange) {
        return new UnspentOutput(
                address,
                isChange,
                new BigDecimal(o.getValue()),
                o.getIndex(),
                o.getScript(),
                o.getTrxHash(),
                o.getPubKeyHash()
        );
    }

    public static TrialTransaction
    trial(NewTransaction transaction, Receipt payload, List<UnspentOut> spentOuts) {
        return new TrialTransaction(
                transaction.getHash(),
                transaction.getTotalAmount().toBigInteger(),
                transaction.getFee().toBigInteger(),
                transaction.getChange().toBigInteger(),
                payload.getFrom(),
                payload.getTo(),
                transaction.getTrxHex(),
                spentOuts
        );
    }

    public static NewTransaction
    transaction(Account account, Address address,
                Receipt payload, BigDecimal feePerKb,
                List<UnspentOutput> unspentOutputs,
                Network network, String derivation) {
        return new NewTransaction.Builder()
                .parameters(network)
                .deterministic(derivation)
                .privateKey(account.getPrivateKey())
                .chainCode(account.getChainCode())
                .from(new ChainAddress(address.getIndex(), address.getAddress()))
                .to(payload.getTo())
                .amount(new BigDecimal(payload.getValue()))
                .outputs(unspentOutputs)
                .feePerKb(feePerKb)
                .calcUnspentOutput()
                .transaction()
                .build();
    }

    public static FeePerKbDto fromFeePerKb(FeePerKb data) {
        return new FeePerKbDto(
                data.getId(),
                data.getFee()
        );
    }

    public static FeePerKb toFeePerKb(FeePerKbDto data) {
        return new FeePerKb(
                data.getId(),
                data.getFee()
        );
    }

}
