package com.github.orders.controllers.impl;

import com.github.orders.dto.*;
import com.github.orders.entity.*;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrdersDetailControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static final Long ORDER_ID = 1L;

    public static final Long CUSTOMER_ID = 1L;

    public static final BigDecimal AMOUNT = new BigDecimal("1.0020");

    public static final String CUSTOMER_NAME = "Alex";

    public static final String CUSTOMER_ADDRESS = "Walker street 12";

    public static final String CUSTOMER_EMAIL = "Alex@gmail.com";

    public static final String CUSTOMER_PHONE = "+780567445";

    public static final List<Long> PRODUCT_IDS = Lists.newArrayList(1L, 2L, 3L);

    public static CustomerDto customerDto() {
        return new CustomerDto(
                CUSTOMER_ID,
                CUSTOMER_NAME,
                CUSTOMER_EMAIL,
                CUSTOMER_PHONE,
                addressDto()
        );
    }

    public static OrderDetailDto orderDetailDto() {
        return new OrderDetailDto(
                ORDER_ID,
                customerDto(),
                ITEMS_FOR_EQ,
                AMOUNT,
                BigDecimal.TEN,
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                BigDecimal.TEN,
                OrderStatus.open
        );
    }

    public static OrderDetailDto orderForExpected() {
        return new OrderDetailDto(
                ORDER_ID,
                customerDto(),
                ITEMS_FOR_EQ,
                AMOUNT,
                BigDecimal.TEN,
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                BigDecimal.TEN,
                OrderStatus.open
        );
    }

    public static OrderDetailDto payload() {
        return new OrderDetailDto(
                customerDto(),
                ITEMS_FOR_SA,
                AMOUNT,
                BigDecimal.TEN,
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                BigDecimal.TEN,
                OrderStatus.open
        );
    }

    public static OrderDetail orderDetail() {
        return new OrderDetail(
                CUSTOMER_ID,
                AMOUNT,
                BigDecimal.ZERO,
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                BigDecimal.TEN,
                USER_ID,
                OrderStatus.open
        );
    }

    public static List<SpecificationDto> SPECIFICATIONS = List.of(
            specificationOne(),
            specificationTwo(),
            specificationThree()
    );

    public static SpecificationDto specificationOne() {
        return new SpecificationDto(
                1L,
                "specification1",
                "This is specification1"
        );
    }

    public static SpecificationDto specificationTwo() {
        return new SpecificationDto(
                2L,
                "specification2",
                "This is specification2"
        );
    }

    public static SpecificationDto specificationThree() {
        return new SpecificationDto(
                2L,
                "specification2",
                "This is specification2"
        );
    }

    public static List<CommentDto> COMMENTS = List.of(
            commentOne(),
            commentTwo(),
            commentThree()
    );

    public static CommentDto commentOne() {
        return new CommentDto(
                1L,
                "comment1",
                "This is comment1"
        );
    }

    public static CommentDto commentTwo() {
        return new CommentDto(
                2L,
                "comment2",
                "This is comment2"
        );
    }

    public static CommentDto commentThree() {
        return new CommentDto(
                3L,
                "comment3",
                "This is comment3"
        );
    }

    public static List<OrderItemDto> ITEMS_FOR_EQ = List.of(
            new OrderItemDto(
                    1L,
                    new ProductDto(
                            1L,
                            "Brand1",
                            "Nokia",
                            new BigDecimal("12.2"),
                            25,
                            "This is good product.",
                            "img",
                            Lists.newArrayList("1", "2", "3"),
                            SPECIFICATIONS,
                            COMMENTS,
                            "subcategory1",
                            0
                    ),
                    1,
                    BigDecimal.TEN,
                    BigDecimal.ZERO
            ),
            new OrderItemDto(
                    2L,
                    new ProductDto(
                            2L,
                            "Brand2",
                            "IPhone",
                            new BigDecimal("100.2"),
                            100,
                            "This is good product.",
                            "img",
                            Lists.newArrayList("1", "2", "3"),
                            SPECIFICATIONS,
                            COMMENTS,
                            "subcategory1",
                            0
                    ),
                    1,
                    BigDecimal.TEN,
                    BigDecimal.ZERO
            )
    );

    public static List<OrderItemDto> ITEMS_FOR_SA = List.of(
            new OrderItemDto(
                    null,
                    new ProductDto(
                            1L,
                            "Brand1",
                            "Nokia",
                            new BigDecimal("12.2"),
                            25,
                            "This is good product.",
                            "img",
                            Lists.newArrayList("1", "2", "3"),
                            SPECIFICATIONS,
                            COMMENTS,
                            "subcategory1",
                            0
                    ),
                    1,
                    BigDecimal.TEN,
                    BigDecimal.ZERO
            ),
            new OrderItemDto(
                    null,
                    new ProductDto(
                            2L,
                            "Brand2",
                            "IPhone",
                            new BigDecimal("100.2"),
                            100,
                            "This is good product.",
                            "img",
                            Lists.newArrayList("1", "2", "3"),
                            SPECIFICATIONS,
                            COMMENTS,
                            "subcategory1",
                            0
                    ),
                    1,
                    BigDecimal.TEN,
                    BigDecimal.ZERO
            )
    );

    public static AddressDto addressDto() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000,
                "customer"
        );
    }

}
