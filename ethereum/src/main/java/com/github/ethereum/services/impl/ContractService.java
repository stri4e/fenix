package com.github.ethereum.services.impl;

import com.github.ethereum.entity.Contract;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.exceptions.NotFound;
import com.github.ethereum.repository.ContractRepo;
import com.github.ethereum.services.IContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService implements IContractService {

    private final ContractRepo contractRepo;

    @Override
    public Contract create(Contract contract) {
        return this.contractRepo.save(contract);
    }

    @Override
    public List<Contract> readAllContracts() {
        return this.contractRepo.findAll();
    }

    @Override
    public List<String> readAllAddresses() {
        return this.contractRepo.findAllAddress();
    }

    @Override
    public Contract readByName(String name) {
        return this.contractRepo.findByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Contract readByAddress(String address) {
        return this.contractRepo.findByAddress(address)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Contract contract) {
        this.contractRepo.save(contract);
    }

    @Override
    public void updateStatus(Long id, EntityStatus status) {
        this.contractRepo.updateStatus(id, status);
    }
}
