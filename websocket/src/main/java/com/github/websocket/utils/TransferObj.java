package com.github.websocket.utils;

import com.github.websocket.dto.*;
import com.github.websocket.payload.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransferObj {

    public static OrderDto
    fromOrderDetail(OrderDetail data, List<Product> products) {
        var productsDto = products.stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
        var customer = fromCustomer(data.getCustomer());
        return new OrderDto(
                data.getId(),
                customer,
                productsDto,
                data.getAmount(),
                data.getUserId(),
                data.getStatus(),
                fromDelivery(data.getDelivery())
        );
    }

    public static CustomerDto fromCustomer(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerEmail(),
                customer.getCustomerPhone()
        );
    }

    public static ProductDto fromProduct(Product data) {
        if (Objects.isNull(data)) {
            return null;
        }
        ProductDto p = new ProductDto();
        BeanUtils.copyProperties(data, p);
        return p;
    }

    public static DeliveryDto fromDelivery(Delivery data) {
        return new DeliveryDto(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                data.getBranchAddress()
        );
    }

}
