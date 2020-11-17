package com.github.admins.controllers.impl;

import com.github.admins.dto.*;
import com.github.admins.payload.DeliveryType;
import com.github.admins.payload.OrderStatus;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticsControllerMocks {

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
