package com.github.ethereum.services;

import com.github.ethereum.entity.Contract;
import com.github.ethereum.entity.EntityStatus;

import java.util.List;

public interface IContractService {

    Contract create(Contract contract);

    List<Contract> readAllContracts();

    List<String> readAllAddresses();

    Contract readByName(String name);

    Contract readById(Long id);

    Contract readByAddress(String address);

    void update(Long id, String name, String address);

    void updateStatus(Long id, EntityStatus status);

}
