package com.github.orders.controllers.impl;

import com.github.orders.controllers.ICustomerController;
import com.github.orders.dto.CustomerDto;
import com.github.orders.entity.OrderDetail;
import com.github.orders.service.ICustomerService;
import com.github.orders.service.IOrderDetailService;
import com.github.orders.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.github.orders.utils.TransferObj.fromCustomer;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/customers")
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;

    @Override
    public CustomerDto findCustomer(UUID userId) {
        return fromCustomer(this.customerService.readByUserId(userId));
    }

    @Override
    public void updateCustomer(CustomerDto payload) {
        this.customerService.update(
                payload.getId(),
                payload.getCustomerName(),
                payload.getCustomerAddress(),
                payload.getCustomerEmail(),
                payload.getCustomerPhone()
        );
    }

}

