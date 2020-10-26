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

import static com.github.orders.utils.TransferObj.fromCompany;
import static com.github.orders.utils.TransferObj.toCompany;

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
    public CompanyDto save(CompanyDto payload) {
        return fromCompany(this.companyService.create(toCompany(payload)));
    }

    @Override
    public CompanyDto findById(Long id) {
        return fromCompany(this.companyService.readById(id));
    }

    @Override
    public void update(CompanyDto payload) {
        Company company = this.companyService.readById(payload.getId());
        company.setName(payload.getName());
        company.setCities(payload.getCities());
        this.companyService.update(company);
    }

    @Override
    public void delete(Long id) {
        this.companyService.remove(id);
    }
}
