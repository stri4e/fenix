package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.IDeliveryCompaniesController;
import com.github.deliveries.dto.CompanyDto;
import com.github.deliveries.services.ICompanyService;
import com.github.deliveries.utils.Logging;
import com.github.deliveries.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.deliveries.utils.TransferObj.fromCompany;
import static com.github.deliveries.utils.TransferObj.toCompany;

@RestController
@RequestMapping("/v1/companies")
@RequiredArgsConstructor
public class DeliveryCompaniesController implements IDeliveryCompaniesController {

    private final ICompanyService companyService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public List<CompanyDto> findAll() {
        return this.companyService.readAll().stream()
                .map(TransferObj::fromCompany)
                .collect(Collectors.toList());
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public CompanyDto save(CompanyDto payload) {
        return fromCompany(this.companyService.create(toCompany(payload)));
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public CompanyDto findById(Long id) {
        return fromCompany(this.companyService.readById(id));
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void update(CompanyDto payload) {
        this.companyService.update(payload.getId(), payload.getName());
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.companyService.remove(id);
    }
}
