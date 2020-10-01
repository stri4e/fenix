package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IContractController;
import com.github.ethereum.dto.ContractDto;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.services.IContractService;
import com.github.ethereum.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.ethereum.utils.TransferObj.fromContract;
import static com.github.ethereum.utils.TransferObj.toContract;

@RestController
@RequiredArgsConstructor
public class ContractController implements IContractController {

    private final IContractService contractService;

    @Override
    public ContractDto createContract(@Valid ContractDto payload) {
        return fromContract(this.contractService.create(toContract(payload)));
    }

    @Override
    public List<ContractDto> findAll() {
        return this.contractService
                .readAllContracts()
                .stream().map(TransferObj::fromContract)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ContractDto payload) {

    }

    @Override
    public void delete(Long id) {
        this.contractService.updateStatus(id, EntityStatus.off);
    }
}
