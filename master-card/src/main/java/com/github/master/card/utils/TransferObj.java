package com.github.master.card.utils;

import com.github.facade.master.card.payload.MastercardPaymentTransfer;
import com.github.facade.master.card.utils.Constant;
import com.github.master.card.entity.Account;
import com.github.master.card.entity.Transaction;
import com.github.master.card.payload.Receipt;

import java.math.BigInteger;

public class TransferObj {

    public static MastercardPaymentTransfer toMastercard(Receipt receipt, Account account) {
        return new MastercardPaymentTransfer(
                account.getPartnerId(), receipt.getAmount().toPlainString(), receipt.getCurrency(),
                receipt.getIdentifier(), receipt.getExpYear(), receipt.getExpMonth(),
                receipt.getCvc().toString(), receipt.getAccountUri(), receipt.getFirstName(),
                receipt.getLastName(), receipt.getAddress(), receipt.getCity(),
                receipt.getPostCode().toString(), receipt.getCountrySubdivision(),
                receipt.getCountry(), Constant.CREDIT, account.getUriScheme(),
                account.getIdentifier(), account.getExpYear(), account.getExpMonth(),
                account.getCvc().toString(), account.getAccountUri(), account.getFirstName(),
                account.getLastName(), account.getAddress(), account.getCity(),
                account.getPostCode().toString(), account.getAccountName()
        );
    }

    public static Transaction toTransaction(Long billId, String transferReference, MastercardPaymentTransfer transfer, Account account) {
        return new Transaction(
                billId, transferReference, transfer.getPaymentType(), new BigInteger(transfer.getAmount()),
                transfer.getCurrency(), transfer.getSenderFirstName(), transfer.getSenderLastName(),
                transfer.getSenderAddressLine1(), transfer.getSenderCity(), transfer.getSenderCountry(), account
        );
    }

}
