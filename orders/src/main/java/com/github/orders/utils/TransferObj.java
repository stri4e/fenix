package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.payload.Category;
import com.github.orders.payload.Comment;
import com.github.orders.payload.Product;
import com.github.orders.payload.Specification;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransferObj {

    public static Customer toCustomer(CustomerDto data) {
        return new Customer(
                data.getCustomerName(),
                data.getCustomerAddress(),
                data.getCustomerEmail(),
                data.getCustomerPhone()
        );
    }

    public static OrderDetail toOrderDetail(
            Customer c, List<Long> productIds,
            BigDecimal amount, Long userId, OrderStatus status) {
        return new OrderDetail(c, productIds, amount, userId, status);
    }

    public static OrderDetailEntryDto
    fromOrderDetailDto(OrderDetail data, List<Product> products) {
        var customer = fromCustomer(data.getCustomer());
        var productsDto = products.stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
        return new OrderDetailEntryDto(
                data.getId(),
                customer,
                productsDto,
                data.getAmount(),
                data.getUserId(),
                data.getStatus()
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

    public static SpecificationDto fromSpecification(Specification data) {
        return new SpecificationDto(
                data.getId(),
                data.getName(),
                data.getDescription()
        );
    }

    public static CommentDto fromComment(Comment data) {
        return new CommentDto(data.getId(), data.getName(), data.getComment());
    }

    public static CategoryDto fromCategory(Category data) {
        return new CategoryDto(data.getId(), data.getName());
    }

}
