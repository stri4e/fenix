package com.github.orders.controllers.impl;

import com.github.orders.controllers.IDeliveryCompaniesController;
import com.github.orders.dto.CompanyDto;
import com.github.orders.entity.Company;
import com.github.orders.service.ICompanyService;
import com.github.orders.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/delivery/company")
@RequiredArgsConstructor
public class DeliveryCompaniesController implements IDeliveryCompaniesController {

    private final ICompanyService companyService;

    @Override
    public List<CompanyDto> findAll() {
        List<Company> companies = this.companyService.readAll();
        return companies.stream()
                .map(TransferObj::fromCompany)
                .collect(Collectors.toList());
    }

    @Override
    public Company save(Company payload) {
        return this.companyService.create(payload);
    }

    @Override
    public Company findById(Long id) {
        return this.companyService.readById(id);
    }

    @Override
    public void update(Company payload) {
        this.companyService.update(payload);
    }

    @Override
    public void delete(Long id) {
        this.companyService.remove(id);
    }
}
