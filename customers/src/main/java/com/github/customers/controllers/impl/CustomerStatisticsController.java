package com.github.customers.controllers.impl;

import com.github.customers.controllers.ICustomerStatisticsController;
import com.github.customers.dto.CustomerStatisticsDto;
import com.github.customers.services.ICustomerStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.customers.utils.TransferObj.fromCustomerStatistics;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/statistics")
public class CustomerStatisticsController implements ICustomerStatisticsController {

    private final ICustomerStatisticsService customerStatisticsService;

    @Override
    public CustomerStatisticsDto findById(Long customerId) {
        return fromCustomerStatistics(this.customerStatisticsService.readByCustomerId(customerId));
    }

    @Override
    public void updateTotalOrders(Long customerId, Integer totalOrders) {
        this.customerStatisticsService.updateTotalOrders(customerId, totalOrders);
    }

    @Override
    public void updateSuccessOrders(Long customerId, Integer successOrders) {
        this.customerStatisticsService.updateSuccessOrders(customerId, successOrders);
    }

    @Override
    public void updateReturnedOrders(Long customerId, Integer returnedOrders) {
        this.customerStatisticsService.updateReturnedOrders(customerId, returnedOrders);
    }

}
