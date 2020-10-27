package com.github.admins.controllers.impl;

import com.github.admins.controllers.IEthereumAccountController;
import com.github.admins.dto.EthereumAccountDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IEthereumAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/ethereum/accounts")
@RequiredArgsConstructor
public class EthereumAccountController implements IEthereumAccountController {

    private final IEthereumAccountService ethereumAccountService;

    @Override
    public Page<EthereumAccountDto> findByStatus(EntityStatus status, Pageable pageable) {
        return this.ethereumAccountService.findByStatus(status, pageable);
    }
}
