package com.github.accounts.controllers.impl;

import com.github.accounts.controllers.IViewsController;
import com.github.accounts.entity.Account;
import com.github.accounts.entity.View;
import com.github.accounts.services.IAccountsService;
import com.github.accounts.services.IViewsService;
import com.github.accounts.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/views")
public class ViewsController implements IViewsController {

    private final IViewsService viewsService;

    private final IAccountsService accountsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateViews(Long accountId, Long productId) {
        if (this.viewsService.existsByProductId(productId)) {
            View views = this.viewsService.readByProductId(productId).updateAt();
            this.viewsService.createOrUpdate(views);
        } else {
            View view = this.viewsService.createOrUpdate(new View(productId));
            Account account = this.accountsService.readById(accountId).view(view);
            this.accountsService.update(account);
        }
    }

}
