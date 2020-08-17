package com.github.orders.controllers.impl;

import com.github.orders.dto.*;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.payload.Product;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class OrdersDetailControllerMocks {

    public static final Long USER_ID = 1L;

    public static final Long ORDER_ID = 1L;

    public static final Long CUSTOMER_ID = 1L;

    public static final BigDecimal AMOUNT = new BigDecimal("1.002");

    public static final String CUSTOMER_NAME = "Alex";

    public static final String CUSTOMER_ADDRESS = "Walker street 12";

    public static final String CUSTOMER_EMAIL = "Alex@gmail.com";

    public static final String CUSTOMER_PHONE = "+780567445";

    public static final List<Long> PRODUCT_IDS = Lists.newArrayList(1L, 2L, 3L);

    public static CustomerDto customerDto() {
        return new CustomerDto(
                CUSTOMER_ID,
                CUSTOMER_NAME,
                CUSTOMER_ADDRESS,
                CUSTOMER_EMAIL,
                CUSTOMER_PHONE
        );
    }

    public static OrderDetailDto orderDetailDto() {
        return new OrderDetailDto(ORDER_ID, customerDto(), PRODUCT_IDS, AMOUNT, OrderStatus.open);
    }

    public static OrderDetailDto payload() {
        return new OrderDetailDto(customerDto(), PRODUCT_IDS, AMOUNT, OrderStatus.open);
    }

    public static OrderDetail orderDetail() {
        return new OrderDetail(customer(), PRODUCT_IDS, AMOUNT, USER_ID, OrderStatus.open);
    }

    public static Customer customer() {
        return new Customer(
                CUSTOMER_ID,
                CUSTOMER_NAME,
                CUSTOMER_ADDRESS,
                CUSTOMER_EMAIL,
                CUSTOMER_PHONE
        );
    }

    public static OrderDetail expOrder() {
        return new OrderDetail(ORDER_ID, customer(), PRODUCT_IDS, AMOUNT, USER_ID, OrderStatus.open);
    }

    public static OrderDetail orderDetailForUpdate() {
        return new OrderDetail(customer(), PRODUCT_IDS, BigDecimal.ONE, USER_ID, OrderStatus.open);
    }

    public static final List<Product> PRODUCTS = com.google.common.collect.Lists.newArrayList(
            new Product(
                    1L,
                    "Nokia",
                    new BigDecimal("12.2"),
                    25,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            ),
            new Product(
                    2L,
                    "IPhone",
                    new BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            ),
            new Product(
                    3L,
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            )
    );

    public static final List<ProductDto> PRODUCTS_DTO = Lists.newArrayList(
            new ProductDto(
                    1L,
                    "Nokia",
                    new BigDecimal("12.2"),
                    25,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            ),
            new ProductDto(
                    2L,
                    "IPhone",
                    new BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            ),
            new ProductDto(
                    3L,
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            )
    );

    public static OrderDto orderDetailEntryDto() {
        return new OrderDto(
                ORDER_ID,
                customerDto(),
                PRODUCTS_DTO,
                AMOUNT,
                OrderStatus.open
        );
    }

    public static PurchaseDto purchaseDto() {
        return new PurchaseDto(USER_ID, orderDetailEntryDto());
    }

}
