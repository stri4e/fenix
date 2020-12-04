package com.github.statistics.services;

import com.github.statistics.entity.Client;

public interface IClientsService {

    Client create(Client client);

    Long countAll();

}
