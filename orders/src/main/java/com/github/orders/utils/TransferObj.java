package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.payload.Category;
import com.github.orders.payload.Comment;
import com.github.orders.payload.Product;
import com.github.orders.payload.Specification;

import java.math.BigDecimal;
import java.util.List;
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

    public static ProductDetailDto fromProduct(Product p) {
        var specifications = p.getSpecification().stream()
                .map(TransferObj::fromSpecification)
                .collect(Collectors.toList());
        var comments = p.getComments().stream()
                .map(TransferObj::fromComment)
                .collect(Collectors.toList());
        var category = fromCategory(p.getCategory());
        return new ProductDetailDto(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getDescription(),
                p.getPreviewImage(),
                p.getImages(),
                specifications,
                comments,
                category
        );
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
