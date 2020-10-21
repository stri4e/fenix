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

    @Value(value = "${app.is.debug}")
    private Boolean isDebug;

    @Value(value = "${app.is.sandbox}")
    private Boolean isSandbox;

    @Override
    public String send(MastercardPaymentTransfer transfer, Account account) {
        IFacadeMasterCard facade = new FacadeMasterCard(
                account.getConsumerKey(),
                account.getKeyAlias(),
                account.getKeyPassword(),
                account.getPrivateKey(),
                this.isDebug, this.isSandbox
        );
        return facade.send(transfer).orElseThrow(PaymentRequired::new);
    }

}
