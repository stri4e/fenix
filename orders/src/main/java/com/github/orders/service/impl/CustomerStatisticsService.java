package com.github.orders.service.impl;

import com.github.orders.service.ICustomerStatisticsService;
import org.springframework.stereotype.Service;

@Service
public class CustomerStatisticsService implements ICustomerStatisticsService {

    @Override
    public void updateTotalOrders(Long customerId, Integer totalOrders) {

    }

    @Override
    public void updateSuccessOrders(Long customerId, Integer successOrders) {

    }

    @Override
    public void updateReturnedOrders(Long customerId, Integer returnedOrders) {

    }
}
