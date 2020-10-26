package com.github.admins.controllers.impl;

import com.github.admins.controllers.IBranchCompanyController;
import com.github.admins.dto.BranchDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IBranchCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/delivery/company/branch")
@RequiredArgsConstructor
public class BranchCompanyController implements IBranchCompanyController {

    private final IBranchCompanyService branchCompanyService;

    @Override
    public BranchDto findById(Long id) {
        return this.branchCompanyService.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public BranchDto save(Long companyId, BranchDto payload) {
        return this.branchCompanyService.create(companyId, payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    public void update(BranchDto payload) {
        this.branchCompanyService.update(payload);
    }

    @Override
    public void delete(Long id) {
        this.branchCompanyService.delete(id);
    }
}
