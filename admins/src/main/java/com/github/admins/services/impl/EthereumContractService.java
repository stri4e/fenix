package com.github.admins.services.impl;

import com.github.admins.dto.EthereumContractDto;
import com.github.admins.services.IEthereumContractService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class EthereumContractService implements IEthereumContractService {

    @Override
    public EthereumContractDto create(@Valid EthereumContractDto payload) {
        return null;
    }

    @Override
    public void update(EthereumContractDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
