package com.github.websocket.controllers;

import com.github.websocket.dto.*;
import com.github.websocket.payload.BillType;
import com.github.websocket.payload.DeliveryType;
import com.github.websocket.payload.OrderStatus;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OrderControllerMocks {

    public static final Long ORDER_ID = 1L;

    public static final Long CUSTOMER_ID = 1L;

    public static final BigDecimal AMOUNT = new BigDecimal("1.002");

    public static final String CUSTOMER_NAME = "Alex";

    public static final String CUSTOMER_ADDRESS = "Walker street 12";

    public static final String CUSTOMER_EMAIL = "Alex@gmail.com";

    public static final String CUSTOMER_PHONE = "+780567445";

    public static List<CommentDto> COMMENTS = List.of(
            commentOne(),
            commentTwo(),
            commentThree()
    );

    public static List<SpecificationDto> SPECIFICATIONS = List.of(
            specificationOne(),
            specificationTwo(),
            specificationThree()
    );

    public static OrderDetailDto request() {
        return new OrderDetailDto(
                ORDER_ID,
                customerDto(),
                ITEMS_FOR_EQ,
                AMOUNT,
                OrderStatus.open,
                delivery()
        );
    }

    public static CustomerDto customerDto() {
        return new CustomerDto(
                CUSTOMER_ID,
                CUSTOMER_NAME,
                CUSTOMER_ADDRESS,
                CUSTOMER_EMAIL,
                CUSTOMER_PHONE
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

    public static DeliveryDto delivery() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Nowa poshta",
                "xz street",
                new BigDecimal("50.200")
        );
    }

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

}
