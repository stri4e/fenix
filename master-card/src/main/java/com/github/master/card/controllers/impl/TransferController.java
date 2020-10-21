package com.github.master.card.controllers.impl;

import com.github.facade.master.card.payload.MastercardPaymentTransfer;
import com.github.facade.master.card.utils.Constant;
import com.github.master.card.controllers.ITransferController;
import com.github.master.card.entity.Account;
import com.github.master.card.entity.Transaction;
import com.github.master.card.payload.Receipt;
import com.github.master.card.services.IAccountService;
import com.github.master.card.services.IBillService;
import com.github.master.card.services.IMasterCardService;
import com.github.master.card.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payment")
public class TransferController implements ITransferController {

    private final IMasterCardService masterCardService;

    private final IAccountService accountService;

    private final ITransactionService transactionService;

    private final IBillService billService;

    @Override
    public void sendTransfer(Long billId, Receipt payload) {
        Account account = this.accountService.readActive();
        var amount = payload.getAmount();
        MastercardPaymentTransfer transfer = new MastercardPaymentTransfer(
                account.getPartnerId(), amount.toPlainString(), payload.getCurrency(),
                payload.getIdentifier(), payload.getExpYear(), payload.getExpMonth(),
                payload.getCvc().toString(), payload.getAccountUri(), payload.getFirstName(),
                payload.getLastName(), payload.getAddress(), payload.getCity(),
                payload.getPostCode().toString(), payload.getCountrySubdivision(),
                payload.getCountry(), Constant.CREDIT, account.getUriScheme(),
                account.getIdentifier(), account.getExpYear(), account.getExpMonth(),
                account.getCvc().toString(), account.getAccountUri(), account.getFirstName(),
                account.getLastName(), account.getAddress(), account.getCity(),
                account.getPostCode().toString(), account.getAccountName()
        );
        var transferReference = this.masterCardService.send(transfer, account);
        Transaction transaction = new Transaction(
                billId, transferReference, transfer.getPaymentType(), new BigInteger(transfer.getAmount()),
                transfer.getCurrency(), transfer.getSenderFirstName(), transfer.getSenderLastName(),
                transfer.getSenderAddressLine1(), transfer.getSenderCity(), transfer.getSenderCountry(), account
        );
        this.transactionService.create(transaction);
        this.billService.update(billId, amount.toBigInteger(), transferReference);
    }

}
