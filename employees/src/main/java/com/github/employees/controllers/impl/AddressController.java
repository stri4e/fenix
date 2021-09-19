package com.github.employees.controllers.impl;

import com.github.employees.controllers.IAccountController;
import com.github.employees.controllers.IAddressController;
import com.github.employees.entities.EntityStatus;
import com.github.employees.payload.AccountDto;
import com.github.employees.services.IAccountService;
import com.github.employees.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.github.employees.utils.TransferObj.toAccount;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/addresses")
public class AddressController implements IAddressController {
}
