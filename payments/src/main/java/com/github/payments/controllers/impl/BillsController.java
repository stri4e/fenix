package com.github.payments.controllers.impl;

import com.github.payments.controllers.IBillsController;
import com.github.payments.service.IBillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/crypto/bills")
public class BillsController implements IBillsController {

    private final IBillsService billService;

}
