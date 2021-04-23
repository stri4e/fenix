package com.github.admins.controllers.impl;

import com.github.admins.dto.*;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailControllerMocks {

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

    public static OrderDetailDto orderDetailDto() {
        return new OrderDetailDto(
                2L,
                new CustomerDto(
                        2L,
                        "Zigmud Tupizin",
                        "River street 8",
                        "zigmud@gmail.com",
                        "+84384328423",
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
        );
    }

    public static OrderDetailDto orderDetail() {
        return new OrderDetailDto(
                2L,
                new CustomerDto(
                        2L,
                        "Zigmud Tupizin",
                        "River street 8",
                        "zigmud@gmail.com",
                        "+84384328423",
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

    public static final List<OrderDetailDto> ORDERS_CLOSE_DTO = Lists.newArrayList(
            new OrderDetailDto(
                    5L,
                    new CustomerDto(
                            5L,
                            "Kozulinskiy Peronion",
                            "Bolotnaj street 18",
                            "kozulinskiy@yandex.com",
                            addressDto()
                    ),
                    ITEMS_FOR_EQ,
                    new BigDecimal("123432.2432"),
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
                    "close"
            )
    );

    public static final List<OrderDetailDto> ORDERS_DTO_OPEN_DTO = Lists.newArrayList(
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

    public static final List<OrderDetailDto> ORDERS_DTO_HANDLING = Lists.newArrayList(
            new OrderDetailDto(
                    3L,
                    new CustomerDto(
                            3L,
                            "Klava Marinez",
                            "Suspect street 8",
                            "klava@rambler.ru",
                            addressDto()
                    ),
                    ITEMS_FOR_EQ,
                    new BigDecimal("569.205"),
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
                    "handling"
            )
    );

    public static final List<OrderDetailDto> ORDERS_OPEN = Lists.newArrayList(
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

    public static final List<OrderDetailDto> ORDERS_HANDLING = Lists.newArrayList(
            new OrderDetailDto(
                    3L,
                    new CustomerDto(
                            3L,
                            "Klava Marinez",
                            "Suspect street 8",
                            "klava@rambler.ru",
                            addressDto()
                    ),
                    ITEMS_FOR_EQ,
                    new BigDecimal("569.205"),
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
                    "handling"
            )
    );

    public static final List<ProductDto> PRODUCTS = Lists.newArrayList(
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
                    "subcategory",
                    0
            ),
            new ProductDto(
                    2L,
                    "Brand1",
                    "IPhone",
                    new BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory",
                    0
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
