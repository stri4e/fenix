package com.github.statistics.services.impl;

import com.github.statistics.entity.Client;
import com.github.statistics.entity.EntityStatus;
import com.github.statistics.repository.ClientsRepo;
import com.github.statistics.services.IClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientsService implements IClientsService {

    private final ClientsRepo clientsRepo;

    @Override
    public Client create(Client client) {
        return this.clientsRepo.save(client);
    }

    @Override
    public Long countAll() {
        return this.clientsRepo.countAllByStatus(EntityStatus.on);
    }

}
