package com.github.orders.service.impl;

import com.github.orders.entity.Branch;
import com.github.orders.entity.EntityStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.repository.BranchRepo;
import com.github.orders.service.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchService implements IBranchService {

    private final BranchRepo branchRepo;

    @Override
    public Branch readById(Long id) {
        return this.branchRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Branch create(Branch branch) {
        return this.branchRepo.save(branch);
    }

    @Override
    public void update(Branch branch) {
        this.branchRepo.save(branch);
    }

    @Override
    public void remove(Long id) {
        this.branchRepo.updateStatus(id, EntityStatus.off);
    }
}
