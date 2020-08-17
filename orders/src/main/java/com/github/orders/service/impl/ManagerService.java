package com.github.orders.service.impl;

import com.github.orders.entity.Manager;
import com.github.orders.repository.ManagerRepo;
import com.github.orders.service.IManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService implements IManagerService {

    private final ManagerRepo mangerRepo;

    @Override
    public Manager create(Manager manager) {
        return this.mangerRepo.save(manager);
    }

}
