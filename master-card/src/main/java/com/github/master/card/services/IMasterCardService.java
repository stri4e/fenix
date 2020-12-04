package com.github.master.card.services;

import com.github.facade.master.card.payload.MastercardPaymentTransfer;
import com.github.master.card.entity.Account;

public interface IMasterCardService {

    String send(MastercardPaymentTransfer transfer, Account account);

}
