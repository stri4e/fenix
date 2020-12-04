package com.github.master.card.services.impl;

import com.github.facade.master.card.facade.FacadeMasterCard;
import com.github.facade.master.card.facade.IFacadeMasterCard;
import com.github.facade.master.card.payload.MastercardPaymentTransfer;
import com.github.master.card.entity.Account;
import com.github.master.card.exceptions.PaymentRequired;
import com.github.master.card.services.IMasterCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MasterCardService implements IMasterCardService {

    @Override
    public String send(MastercardPaymentTransfer transfer, Account account) {
        IFacadeMasterCard facade = FacadeMasterCard.getInstance();
        return facade.send(transfer).orElseThrow(PaymentRequired::new);
    }

}
