package com.github.master.card.networking;

import com.github.facade.master.card.facade.FacadeMasterCard;
import com.github.master.card.entity.Account;
import com.github.master.card.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Network implements ApplicationRunner {

    private final IAccountService accountService;

    @Value(value = "${app.is.debug}")
    private Boolean isDebug;

    @Value(value = "${app.is.sandbox}")
    private Boolean isSandbox;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = this.accountService.readActive();
        FacadeMasterCard.initial(
                account.getConsumerKey(),
                account.getKeyAlias(),
                account.getKeyPassword(),
                account.getPrivateKey(),
                this.isDebug, this.isSandbox
        );
    }
}
