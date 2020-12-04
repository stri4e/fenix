package com.github.deliveries.services;


import com.github.deliveries.entity.Company;

import java.util.List;

public interface ICompanyService {

    Company create(Company company);

    List<Company> readAll();

    Company readById(Long id);

    void update(Long id, String name);

    void remove(Long id);

}
