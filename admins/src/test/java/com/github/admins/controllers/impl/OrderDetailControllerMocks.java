package com.github.admins.controllers.impl;

import com.github.admins.dto.*;
import com.github.admins.payload.*;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
                        addressDto()
                ),
                PRODUCTS_DTO,
                new BigDecimal("220.254"),
                OrderStatus.open,
                delivery(),
                bill()
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
                        addressDto()
                ),
                PRODUCTS_DTO,
                new BigDecimal("220.254"),
                OrderStatus.open,
                delivery(),
                bill()
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
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory"
            ),
            new ProductDto(
                    2L,
                    "IPhone",
                    new BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory"
            ),
            new ProductDto(
                    3L,
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory"
            ),
            new ProductDto(
                    4L,
                    "Xiaomi",
                    new BigDecimal("14.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory"
            ),
            new ProductDto(
                    5L,
                    "Huawei",
                    new BigDecimal("24.2"),
                    560,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory"
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
                    PRODUCTS_DTO,
                    new BigDecimal("123432.2432"),
                    OrderStatus.close,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("12.2"),
                    OrderStatus.open,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("220.254"),
                    OrderStatus.open,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("1111.11"),
                    OrderStatus.open,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("569.205"),
                    OrderStatus.handling,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("12.2"),
                    OrderStatus.open,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("220.254"),
                    OrderStatus.open,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("1111.11"),
                    OrderStatus.open,
                    delivery(),
                    bill()
            )
    );

    public static final List<OrderDetailDto> ORDERS_CLOSE = Lists.newArrayList(
            new OrderDetailDto(
                    5L,
                    new CustomerDto(
                            5L,
                            "Kozulinskiy Peronion",
                            "Bolotnaj street 18",
                            "kozulinskiy@yandex.com",
                            addressDto()
                    ),
                    PRODUCTS_DTO,
                    new BigDecimal("123432.2432"),
                    OrderStatus.close,
                    delivery(),
                    bill()
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
                    PRODUCTS_DTO,
                    new BigDecimal("569.205"),
                    OrderStatus.handling,
                    delivery(),
                    bill()
            )
    );

    public static final List<ProductDto> PRODUCTS = Lists.newArrayList(
            new ProductDto(
                    1L,
                    "Nokia",
                    new BigDecimal("12.2"),
                    25,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    "subcategory"
            ),
            new ProductDto(
                    2L,
                    "IPhone",
                    new BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    "subcategory"
            ),
            new ProductDto(
                    3L,
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    "subcategory"
            ),
            new ProductDto(
                    4L,
                    "Xiaomi",
                    new BigDecimal("14.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    "subcategory"
            ),
            new ProductDto(
                    5L,
                    "Huawei",
                    new BigDecimal("24.2"),
                    560,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    "subcategory"
            )
    );

    public static BillDto bill() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(54L),
                "ethereum",
                "address",
                new ArrayList<>(),
                "crypto",
                BillType.def,
                new WhoDto(
                        1L,
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        1L,
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

    public static DeliveryDto delivery() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Nowa poshta",
                addressDto(),
                new BigDecimal("50.2")
        );
    }

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
                AddressType.customer
        );
    }

}
