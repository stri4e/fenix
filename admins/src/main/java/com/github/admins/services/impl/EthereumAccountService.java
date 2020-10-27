package com.github.admins.services.impl;

import com.github.admins.dto.EthereumAccountDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IEthereumAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EthereumAccountService implements IEthereumAccountService {
    @Override
    public Page<EthereumAccountDto> findByStatus(EntityStatus status, Pageable pageable) {
        return null;
    }

    @Override
    public void remove(String address) {

    }
}
