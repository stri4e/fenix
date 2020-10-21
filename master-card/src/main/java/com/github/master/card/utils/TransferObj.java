package com.github.master.card.utils;

import com.github.master.card.entity.Account;
import com.github.master.card.model.MastercardSendPaymentTransfer;
import com.github.master.card.payload.Receipt;

public class TransferObj {

    public static MastercardSendPaymentTransfer toMastercard(Receipt receipt, Account account) {
        return MastercardSendPaymentTransfer.builder()

                .build();
    }

}
