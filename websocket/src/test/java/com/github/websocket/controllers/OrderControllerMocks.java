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

    public static OrderDetailDto request() {
        return new OrderDetailDto(
                ORDER_ID,
                customerDto(),
                PRODUCTS_DTO,
                AMOUNT,
                OrderStatus.open,
                delivery(),
                bill()
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

    public static final List<ProductDto> PRODUCTS_DTO = Lists.newArrayList(
            new ProductDto(
                    1L,
                    "Brand1",
                    "Nokia",
                    new BigDecimal("12.2"),
                    25,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory1"
            ),
            new ProductDto(
                    2L,
                    "Brand2",
                    "IPhone",
                    new BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory1"
            ),
            new ProductDto(
                    3L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    null,
                    null,
                    "subcategory1"
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
                "xz street",
                new BigDecimal("50.200")
        );
    }

}
