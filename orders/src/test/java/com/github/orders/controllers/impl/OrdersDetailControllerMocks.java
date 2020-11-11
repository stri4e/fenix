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

    public static final Long BILL_ID = 1L;

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
        return new OrderDetailDto(
                ORDER_ID,
                customerDto(),
                PRODUCTS_DTO,
                AMOUNT,
                OrderStatus.open,
                delivery(),
                requestBill()
        );
    }

    public static OrderDetailDto orderForExpected() {
        return new OrderDetailDto(
                ORDER_ID,
                customerDto(),
                PRODUCTS_DTO,
                AMOUNT,
                OrderStatus.open,
                delivery(),
                responseBill()
        );
    }

    public static OrderDetailDto orderForUpdate() {
        return new OrderDetailDto(
                ORDER_ID,
                customerDto(),
                PRODUCTS_DTO,
                AMOUNT,
                OrderStatus.close,
                delivery(),
                responseBill()
        );
    }

    public static BillDto responseBill() {
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

    public static BillDto requestBill() {
        return new BillDto(
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

    public static OrderDetailDto payload() {
        return new OrderDetailDto(
                customerDto(),
                PRODUCTS_DTO,
                AMOUNT,
                OrderStatus.open,
                delivery(),
                requestBill()
        );
    }

    public static OrderDetail orderDetail() {
        return new OrderDetail(
                customer(),
                PRODUCT_IDS,
                AMOUNT,
                USER_ID,
                BILL_ID,
                OrderStatus.open
        );
    }

    public static Customer customer() {
        return new Customer(
                CUSTOMER_ID,
                CUSTOMER_NAME,
                CUSTOMER_ADDRESS,
                CUSTOMER_EMAIL,
                CUSTOMER_PHONE,
                USER_ID
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
                    SPECIFICATIONS,
                    COMMENTS,
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
                    SPECIFICATIONS,
                    COMMENTS,
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
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1"
            )
    );

    public static DeliveryDto delivery() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Nowa poshta",
                "xz street",
                new BigDecimal("50.2000")
        );
    }

    public static Delivery deliveryForSave() {
        return new Delivery(
                1L,
                DeliveryType.home,
                "Nowa poshta",
                "xz street",
                new BigDecimal("50.2000"),
                USER_ID
        );
    }

}
