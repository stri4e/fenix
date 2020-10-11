package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.ITransactionController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/transactions")
public class TransactionController implements ITransactionController {
}
