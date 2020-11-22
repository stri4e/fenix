package com.github.admins.controllers.impl;

import com.github.admins.dto.*;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticsControllerMocks {

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

    public static LoginDto request() {
        return new LoginDto(
            null,
                new Date(1604499904016L),
                "device",
                "Europe"
        );
    }

    public static LoginDto response() {
        return new LoginDto(
                1L,
                new Date(1604499904016L),
                "device",
                "Europe"
        );
    }

    public static LoginDto one() {
        return new LoginDto(
                1L,
                new Date(1604499904016L),
                "device",
                "Europe"
        );
    }

    public static LoginDto two() {
        return new LoginDto(
                2L,
                new Date(1604499904016L),
                "device2",
                "Europe"
        );
    }

    public static LoginDto three() {
        return new LoginDto(
                3L,
                new Date(1604499904016L),
                "device2",
                "Europe"
        );
    }

    public static LoginDto four() {
        return new LoginDto(
                4L,
                new Date(1604499904016L),
                "device3",
                "Europe"
        );
    }

    public static LoginDto five() {
        return new LoginDto(
                5L,
                new Date(1604499904016L),
                "device4",
                "Europe"
        );
    }

    public static List<LoginDto> LOGINS = List.of(
            one(), two(), three(), four(), five()
    );

    public static ViewDto oneView() {
        return new ViewDto(
                1L,
                new Date(1604499904016L),
                PRODUCTS_DTO
        );
    }

    public static ViewDto twoView() {
        return new ViewDto(
                2L,
                new Date(1604499904016L),
                PRODUCTS_DTO
        );
    }

    public static ViewDto threeView() {
        return new ViewDto(
                3L,
                new Date(1604494304016L),
                PRODUCTS_DTO
        );
    }

    public static ViewDto fourView() {
        return new ViewDto(
                4L,
                new Date(1604494324016L),
                PRODUCTS_DTO
        );
    }

    public static ViewDto fiveView() {
        return new ViewDto(
                5L,
                new Date(1604494321016L),
                PRODUCTS_DTO
        );
    }

    public static List<ViewDto> VIEWS = List.of(
            oneView(), twoView(), threeView(), fourView(), fiveView()
    );

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

    public static final List<ProductDto> PRODUCTS_DTO = Lists.newArrayList(
            new ProductDto(
                    1L,
                    "brand",
                    "Nokia",
                    new BigDecimal("12.2"),
                    25,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory",
                    0
            ),
            new ProductDto(
                    2L,
                    "brand",
                    "IPhone",
                    new BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory",
                    0
            ),
            new ProductDto(
                    3L,
                    "brand",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory",
                    0
            ),
            new ProductDto(
                    4L,
                    "brand",
                    "Xiaomi",
                    new BigDecimal("14.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory",
                    0
            ),
            new ProductDto(
                    5L,
                    "brand",
                    "Huawei",
                    new BigDecimal("24.2"),
                    560,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory",
                    0
            )
    );

    public static final List<OrderDetailDto> ORDERS = Lists.newArrayList(
            new OrderDetailDto(
                    1L,
                    new CustomerDto(
                            1L,
                            "Albert Albert",
                            "Bolotnaj street 8",
                            "albert@yandex.ru",
                            addressDto()
                    ),
                    ITEMS_FOR_EQ,
                    new BigDecimal("12.2"),
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
                    "open"
            ),
            new OrderDetailDto(
                    2L,
                    new CustomerDto(
                            2L,
                            "Zigmud Tupizin",
                            "River street 8",
                            "zigmud@gmail.com",
                            addressDto()
                    ),
                    ITEMS_FOR_EQ,
                    new BigDecimal("220.254"),
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
                    "open"
            ),
            new OrderDetailDto(
                    4L,
                    new CustomerDto(
                            4L,
                            "Jolobock Ivanich",
                            "Tupizina street 8",
                            "jolobock@gmail.com",
                            addressDto()
                    ),
                    ITEMS_FOR_EQ,
                    new BigDecimal("1111.11"),
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
                    "open"
            )
    );

    public static AddressDto addressDto() {
        return new AddressDto(
                1L,
                "Ukraina",
                "Dnepr",
                "Julvern",
                "1",
                null,
                null,
                "49000"
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
