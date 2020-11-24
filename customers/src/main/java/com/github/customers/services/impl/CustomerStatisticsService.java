package com.github.customers.services.impl;

import com.github.customers.entity.CustomerStatistics;
import com.github.customers.exceptions.NotFound;
import com.github.customers.repository.CustomerStatisticsRepo;
import com.github.customers.services.ICustomerStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerStatisticsService implements ICustomerStatisticsService {

    private final CustomerStatisticsRepo customerStatisticsRepo;

    @Override
    public CustomerStatistics create(CustomerStatistics customerStatistics) {
        return this.customerStatisticsRepo.save(customerStatistics);
    }

    @Override
    public CustomerStatistics readByCustomerId(Long customerId) {
        return this.customerStatisticsRepo.findByCustomerId(customerId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void updateTotalOrders(Long id, Integer totalOrders) {
        this.customerStatisticsRepo.updateTotalOrders(id, totalOrders);
    }

    @Override
    public void updateSuccessOrders(Long id, Integer successOrders) {
        this.customerStatisticsRepo.updateSuccessOrders(id, successOrders);
    }

    @Override
    public void updateReturnedOrders(Long id, Integer returnedOrders) {
        this.customerStatisticsRepo.updateReturnedOrders(id, returnedOrders);
    }

}
