package com.github.customers.controllers.impl;

import com.github.customers.controllers.ICustomerController;
import com.github.customers.dto.CustomerDto;
import com.github.customers.entity.Customer;
import com.github.customers.services.IAccountService;
import com.github.customers.services.ICustomerService;
import com.github.customers.services.ICustomerStatisticsService;
import com.github.customers.utils.Payloads;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.github.customers.entity.CustomerStatistics.defCustomerStat;
import static com.github.customers.utils.TransferObj.fromCustomer;
import static com.github.customers.utils.TransferObj.toCustomer;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;

    private final ICustomerStatisticsService customerStatisticsService;

    private final IAccountService accountService;

    @Override
    public CustomerDto findByUserId(UUID userId) {
        return fromCustomer(this.customerService.readByUserId(userId));
    }

    @Override
    public CustomerDto findById(Long customerId) {
        return fromCustomer(this.customerService.readById(customerId));
    }

    @Override
    public CustomerDto save(UUID userId, CustomerDto payload) {
        Customer customer = this.customerService.create(toCustomer(payload, userId));
        this.customerStatisticsService.create(defCustomerStat(customer));
        return Payloads.of(fromCustomer(customer))
                .doOnNext(c -> this.accountService.updateByCustomer(userId, c));
    }

    @Override
    public CustomerDto saveCustomer(UUID userId, CustomerDto payload) {
        Customer customer = this.customerService.create(toCustomer(payload, userId));
        this.customerStatisticsService.create(defCustomerStat(customer));
        return fromCustomer(customer);
    }

    @Override
    public void updateCustomer(CustomerDto payload) {
        this.customerService.update(
                payload.getId(),
                payload.getFirstName(),
                payload.getLastName(),
                payload.getEmail(),
                payload.getPhone()
        );
        Payloads.async(() -> {
            Customer customer = this.customerService.readById(payload.getId());
            this.accountService.updateByCustomer(customer.getUserId(), payload);
        });
    }

}
