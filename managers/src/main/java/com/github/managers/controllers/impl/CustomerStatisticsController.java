package com.github.managers.controllers.impl;

import com.github.managers.controllers.ICustomerStatisticsController;
import com.github.managers.dto.CustomerStatisticsDto;
import com.github.managers.services.ICustomerStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/customer/statistics")
public class CustomerStatisticsController implements ICustomerStatisticsController {

    private final ICustomerStatisticsService customerStatisticsService;

    @Override
    public CustomerStatisticsDto findById(Long customerId) {
        return this.customerStatisticsService.findById(customerId);
    }

}
