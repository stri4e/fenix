package com.github.websocket.utils;

import com.github.websocket.dto.*;
import com.github.websocket.payload.*;

import java.util.List;
import java.util.stream.Collectors;

public class TransferObj {

    public static OrderDetailEntry
    fromOrderDetail(OrderDetail data, List<Product> products) {
        return new OrderDetailEntry(
                data.getId(),
                fromCustomer(data.getCustomer()),
                products.stream()
                        .map(TransferObj::fromProductDetail)
                        .collect(Collectors.toList()),
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

    public static ProductDetailDto fromProductDetail(Product p) {
        return new ProductDetailDto(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getDescription(),
                p.getPreviewImage(),
                p.getImages(),
                p.getSpecification().stream()
                        .map(TransferObj::fromSpecification)
                        .collect(Collectors.toList()),
                p.getComments().stream()
                        .map(TransferObj::fromComment)
                        .collect(Collectors.toList()),
                fromCategory(p.getCategory())
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
