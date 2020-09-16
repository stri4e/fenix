package com.github.orders.controllers.impl;

import com.github.orders.controllers.IBranchCompanyController;
import com.github.orders.entity.Branch;
import com.github.orders.entity.Company;
import com.github.orders.service.IBranchService;
import com.github.orders.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/delivery/company/branch")
@RequiredArgsConstructor
public class BranchCompanyController implements IBranchCompanyController {

    private final IBranchService branchService;

    private final ICompanyService companyService;

    @Override
    public Branch findById(Long id) {
        return this.branchService.readById(id);
    }

    @Override
    public Branch save(@Valid Branch payload) {
        return this.branchService.create(payload);
    }

    @Override
    public void update(@Valid Branch payload) {
        this.branchService.update(payload);
    }

    @Override
    public void delete(Long id) {
        this.branchService.remove(id);
    }

}
