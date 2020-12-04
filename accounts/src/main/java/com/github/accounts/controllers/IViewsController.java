package com.github.accounts.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface IViewsController {

    @PutMapping(
            path = "/{accountId}/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void updateViews(@PathVariable(value = "accountId") Long accountId,
              @PathVariable(value = "productId") Long productId);

}
