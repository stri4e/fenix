package com.github.admins.controllers.impl;


import com.github.admins.dto.CustomerDto;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.ProductDto;
import com.github.admins.payload.*;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailControllerMocks {

    public static OrderDetailDto orderDetailDto() {
        return new OrderDetailDto(
                2L,
                new CustomerDto(
                        2L,
                        "Zigmud Tupizin",
                        "River street 8",
                        "zigmud@gmail.com",
                        "+8934223"
                ),
                PRODUCTS_DTO,
                new BigDecimal("220.254"),
                2L,
                OrderStatus.open
        );
    }

    public static OrderDetail orderDetail() {
        return new OrderDetail(
                2L,
                new Customer(
                        2L,
                        "Zigmud Tupizin",
                        "River street 8",
                        "zigmud@gmail.com",
                        "+8934223"
                ),
                Lists.newArrayList(1L, 2L, 3L, 4L, 5L),
                new BigDecimal("220.254"),
                2L,
                OrderStatus.open
        );
    }

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
            ),
            new ProductDto(
                    4L,
                    "Xiaomi",
                    new BigDecimal("14.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            ),
            new ProductDto(
                    5L,
                    "Huawei",
                    new BigDecimal("24.2"),
                    560,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
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
                            "+897543223"
                    ),
                    PRODUCTS_DTO,
                    new BigDecimal("123432.2432"),
                    5L,
                    OrderStatus.close
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
                            "+89752323"
                    ),
                    PRODUCTS_DTO,
                    new BigDecimal("12.2"),
                    1L,
                    OrderStatus.open
            ),
            new OrderDetailDto(
                    2L,
                    new CustomerDto(
                            2L,
                            "Zigmud Tupizin",
                            "River street 8",
                            "zigmud@gmail.com",
                            "+8934223"
                    ),
                    PRODUCTS_DTO,
                    new BigDecimal("220.254"),
                    2L,
                    OrderStatus.open
            ),
            new OrderDetailDto(
                    4L,
                    new CustomerDto(
                            4L,
                            "Jolobock Ivanich",
                            "Tupizina street 8",
                            "jolobock@gmail.com",
                            "+87892323"
                    ),
                    PRODUCTS_DTO,
                    new BigDecimal("1111.11"),
                    4L,
                    OrderStatus.open
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
                            "+897527896"
                    ),
                    PRODUCTS_DTO,
                    new BigDecimal("569.205"),
                    3L,
                    OrderStatus.handling
            )
    );

    public static final List<OrderDetail> ORDERS_OPEN = Lists.newArrayList(
            new OrderDetail(
                    1L,
                    new Customer(
                            1L,
                            "Albert Albert",
                            "Bolotnaj street 8",
                            "albert@yandex.ru",
                            "+89752323"
                    ),
                    Lists.newArrayList(1L, 2L, 3L, 4L, 5L),
                    new BigDecimal("12.2"),
                    1L,
                    OrderStatus.open
            ),
            new OrderDetail(
                    2L,
                    new Customer(
                            2L,
                            "Zigmud Tupizin",
                            "River street 8",
                            "zigmud@gmail.com",
                            "+8934223"
                    ),
                    Lists.newArrayList(1L, 2L, 3L, 4L, 5L),
                    new BigDecimal("220.254"),
                    2L,
                    OrderStatus.open
            ),
            new OrderDetail(
                    4L,
                    new Customer(
                            4L,
                            "Jolobock Ivanich",
                            "Tupizina street 8",
                            "jolobock@gmail.com",
                            "+87892323"
                    ),
                    Lists.newArrayList(1L, 2L, 3L, 4L, 5L),
                    new BigDecimal("1111.11"),
                    4L,
                    OrderStatus.open
            )
    );

    public static final List<OrderDetail> ORDERS_CLOSE = Lists.newArrayList(
            new OrderDetail(
                    5L,
                    new Customer(
                            5L,
                            "Kozulinskiy Peronion",
                            "Bolotnaj street 18",
                            "kozulinskiy@yandex.com",
                            "+897543223"
                    ),
                    Lists.newArrayList(1L, 2L, 3L, 4L, 5L),
                    new BigDecimal("123432.2432"),
                    5L,
                    OrderStatus.close
            )
    );

    public static final List<OrderDetail> ORDERS_HANDLING = Lists.newArrayList(
            new OrderDetail(
                    3L,
                    new Customer(
                            3L,
                            "Klava Marinez",
                            "Suspect street 8",
                            "klava@rambler.ru",
                            "+897527896"
                    ),
                    Lists.newArrayList(1L, 2L, 3L, 4L, 5L),
                    new BigDecimal("569.205"),
                    3L,
                    OrderStatus.handling
            )
    );

    public static final List<Product> PRODUCTS = Lists.newArrayList(
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
            ),
            new Product(
                    4L,
                    "Xiaomi",
                    new BigDecimal("14.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            ),
            new Product(
                    5L,
                    "Huawei",
                    new BigDecimal("24.2"),
                    560,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3")
            )
    );

}
