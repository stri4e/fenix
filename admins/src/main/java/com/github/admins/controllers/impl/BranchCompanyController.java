package com.github.admins.controllers.impl;

import com.github.admins.controllers.IBranchCompanyController;
import com.github.admins.dto.BranchDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.Branch;
import com.github.admins.payload.Company;
import com.github.admins.services.IBranchCompanyService;
import com.github.admins.services.IDeliveryCompaniesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.admins.utils.TransferObj.fromBranch;
import static com.github.admins.utils.TransferObj.toBranch;

@RestController
@RequestMapping(path = "/v1/delivery/company/branch")
@RequiredArgsConstructor
public class BranchCompanyController implements IBranchCompanyController {

    private final IBranchCompanyService branchCompanyService;

    private final IDeliveryCompaniesService deliveryCompaniesService;

    @Override
    public BranchDto findById(Long id) {
        return fromBranch(
                this.branchCompanyService.findById(id)
                        .orElseThrow(NotFound::new)
        );
    }

    @Override
    public BranchDto save(Long companyId, BranchDto payload) {
        Company company = this.deliveryCompaniesService.findById(companyId)
                .orElseThrow(NotFound::new);
        Branch branch = this.branchCompanyService.save(toBranch(payload))
                .orElseThrow(BadRequest::new);
        company.addBranch(branch);
        return fromBranch(branch);
    }

    @Override
    public void update(BranchDto payload) {
        this.branchCompanyService.update(toBranch(payload));
    }

    @Override
    public void delete(Long id) {
        this.branchCompanyService.delete(id);
    }
}
