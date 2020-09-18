package com.github.orders.service;

import com.github.orders.entity.Company;

import java.util.List;

public interface ICompanyService {

    Company create(Company company);

    List<Company> readAll();

    Company readById(Long id);

    void update(Company company);

    void remove(Long id);

}
