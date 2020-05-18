package com.github.orders.utils;

import com.github.orders.dto.CustomerDto;
import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.BadRequest;
import com.github.orders.exceptions.TypeMessage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class TransferObj {

    public static Customer customer(CustomerDto data) {
        if (Objects.nonNull(data)) {
            return new Customer(
                    data.getCustomerName(),
                    data.getCustomerAddress(),
                    data.getCustomerEmail(),
                    data.getCustomerPhone()
            );
        }
        throw new BadRequest(TypeMessage.badOrderData);
    }

    public static CustomerDto customerDto(Customer data) {
        return new CustomerDto(
                data.getCustomerName(),
                data.getCustomerAddress(),
                data.getCustomerEmail(),
                data.getCustomerPhone()
        );
    }

    public static OrderDetail orderDetail(
            Customer c, List<Long> productIds,
            BigDecimal amount, Long userId, OrderStatus status) {
        return new OrderDetail(c, productIds, amount, userId, status);
    }

    public static OrderDetailDto orderDetailDto(OrderDetail data) {
        return new OrderDetailDto(
                data.getId(),
                customerDto(data.getCustomer()),
                data.getProductIds(),
                data.getAmount(),
                data.getStatus()
        );
    }

    public static Function<CustomerDto, Customer> orderThrow() {
        return (data) -> new Customer(
                data.getCustomerName(),
                data.getCustomerAddress(),
                data.getCustomerEmail(),
                data.getCustomerPhone()
        );
    }

}
