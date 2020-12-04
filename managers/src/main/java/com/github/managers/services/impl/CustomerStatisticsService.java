package com.github.managers.services.impl;

import com.github.managers.dto.CustomerStatisticsDto;
import com.github.managers.services.ICustomerStatisticsService;
import org.springframework.stereotype.Service;

@Service
public class CustomerStatisticsService implements ICustomerStatisticsService {
    @Override
    public CustomerStatisticsDto findById(Long customerId) {
        return null;
    }
}
