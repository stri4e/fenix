package com.github.orders.service;

import com.github.orders.dto.CustomerDto;

import java.util.Optional;

public interface ICustomerService {

    Optional<CustomerDto> readById(Long id);

}
