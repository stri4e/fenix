package com.github.customers.services;

import com.github.customers.entity.CustomerStatistics;

public interface ICustomerStatisticsService {

    CustomerStatistics create(CustomerStatistics customerStatistics);

    CustomerStatistics readByCustomerId(Long customerId);

    void updateTotalOrders(Long id, Integer totalOrders);

    void updateSuccessOrders(Long id, Integer successOrders);

    void updateReturnedOrders(Long id, Integer returnedOrders);

}
