package com.github.admins.services.impl;

import com.github.admins.dto.ManagerDto;
import com.github.admins.services.IManagersService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagersService implements IManagersService {

    @Override
    public Optional<ManagerDto> findManager(Long orderId) {
        return Optional.empty();
    }
}
