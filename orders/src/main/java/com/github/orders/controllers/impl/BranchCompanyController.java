package com.github.orders.controllers.impl;

import com.github.orders.controllers.IBranchCompanyController;
import com.github.orders.dto.BranchDto;
import com.github.orders.entity.Branch;
import com.github.orders.entity.Company;
import com.github.orders.service.IBranchService;
import com.github.orders.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.orders.utils.TransferObj.fromBranch;
import static com.github.orders.utils.TransferObj.toBranch;

@RestController
@RequestMapping(path = "/v1/delivery/company/branch")
@RequiredArgsConstructor
public class BranchCompanyController implements IBranchCompanyController {

    private final IBranchService branchService;

    private final ICompanyService companyService;

    @Override
    public BranchDto findById(Long id) {
        return fromBranch(this.branchService.readById(id));
    }

    @Override
    public BranchDto save(Long companyId, @Valid BranchDto payload) {
        Company company = this.companyService.readById(companyId);
        Branch branch = this.branchService.create(toBranch(payload));
        company.addBranch(branch);
        return fromBranch(branch);
    }

    @Override
    public void update(@Valid BranchDto payload) {
        Branch branch = this.branchService.readById(payload.getId());
        branch.setNumber(payload.getNumber());
        branch.setAddress(payload.getAddress());
        branch.setPhone(payload.getPhone());
        this.branchService.update(branch);
    }

    @Override
    public void delete(Long id) {
        this.branchService.remove(id);
    }

}
