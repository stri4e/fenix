package com.github.bitcoin.utils;

import com.github.bitcoin.dto.AddressDto;
import com.github.bitcoin.dto.CurrencyDto;
import com.github.bitcoin.dto.TransactionDto;
import com.github.bitcoin.entity.Address;
import com.github.bitcoin.entity.Currency;
import com.github.bitcoin.entity.Transaction;
import com.github.bitcoin.entity.UnspentOut;
import com.github.wrapper.bitcoin.model.TInput;
import com.github.wrapper.bitcoin.model.TOutput;
import com.github.wrapper.bitcoin.model.TransactionData;

import java.math.BigInteger;
import java.util.stream.Collectors;

public class TransferObj {

    public static Transaction toTransaction(TransactionDto data) {
        return new Transaction();
    }

    public static TransactionDto fromTransaction(Transaction data) {
        return new TransactionDto();
    }

    public static Currency toCurrency(Currency data) {
        return new Currency();
    }

    public static CurrencyDto fromCurrency(Currency data) {
        return new CurrencyDto();
    }

    public static Address toAddress(AddressDto data) {
        return new Address();
    }

    public static AddressDto fromAddress(Address data) {
        return new AddressDto();
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

}
