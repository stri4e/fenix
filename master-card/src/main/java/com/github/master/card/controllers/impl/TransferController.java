package com.github.master.card.controllers.impl;

import com.github.facade.master.card.payload.MastercardPaymentTransfer;
import com.github.master.card.controllers.ITransferController;
import com.github.master.card.entity.Account;
import com.github.master.card.entity.Transaction;
import com.github.master.card.payload.Receipt;
import com.github.master.card.services.IAccountService;
import com.github.master.card.services.IBillService;
import com.github.master.card.services.IMasterCardService;
import com.github.master.card.services.ITransactionService;
import com.github.master.card.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.master.card.utils.Converters.toCoin;
import static com.github.master.card.utils.TransferObj.toMastercard;
import static com.github.master.card.utils.TransferObj.toTransaction;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payment")
public class TransferController implements ITransferController {

    private final IMasterCardService masterCardService;

    private final IAccountService accountService;

    private final ITransactionService transactionService;

    private final IBillService billService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void sendTransfer(Long billId, Receipt payload) {
        Account account = this.accountService.readActive();
        var amount = payload.getAmount();
        MastercardPaymentTransfer transfer = toMastercard(payload, account);
        var transferReference = this.masterCardService.send(transfer, account);
        Transaction transaction = toTransaction(billId, transferReference, transfer, account);
        this.transactionService.create(transaction);
        this.billService.update(billId, toCoin(amount), transferReference);
    }

}
