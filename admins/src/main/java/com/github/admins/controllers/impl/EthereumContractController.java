package com.github.admins.controllers.impl;

import com.github.admins.controllers.IEthereumContractController;
import com.github.admins.dto.EthereumContractDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.services.IEthereumContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/ethereum/contracts")
public class EthereumContractController implements IEthereumContractController {

    private final IEthereumContractService ethereumContractService;

    @Override
    public EthereumContractDto save(@Valid EthereumContractDto payload) {
        return this.ethereumContractService.create(payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    public void update(EthereumContractDto payload) {
        this.ethereumContractService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.ethereumContractService.delete(id);
    }
}
