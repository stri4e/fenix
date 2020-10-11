package com.github.bitcoin.utils;

import com.github.bitcoin.dto.AddressDto;
import com.github.bitcoin.dto.CurrencyDto;
import com.github.bitcoin.dto.TransactionDto;
import com.github.bitcoin.entity.Address;
import com.github.bitcoin.entity.Currency;
import com.github.bitcoin.entity.Transaction;

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

}
