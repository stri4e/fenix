package com.github.accounts.controllers.impl;

import com.github.accounts.dto.*;
import com.github.accounts.entity.*;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;

public class AccountsControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static AccountDto response() {
        return new AccountDto(
            1L,
                profile(),
                contact(),
                address(),
                VIEWS_PRODUCT
        );
    }

    public static AccountDto response2() {
        return new AccountDto(
                1L,
                profile(),
                contact(),
                address(),
                new ArrayList<>()
        );
    }

    public static AccountDto response3() {
        return new AccountDto(
                1L,
                profile1(),
                contact1(),
                address1(),
                new ArrayList<>()
        );
    }

    public static AccountDto request() {
        return new AccountDto(
                1L,
                profile(),
                contact(),
                address(),
                new ArrayList<>()
        );
    }

    public static Account accountForSave(Profile profile, Contact contact, Address address, List<View> views) {
        return new Account(
                USER_ID,
                profile,
                contact,
                address,
                new HashSet<>(views)
        );
    }

    public static Profile profileForSave() {
        return new Profile(
                null,
                "Vasia",
                "Kolia",
                "Pupkin",
                "14.22.22",
                Sex.man
        );
    }

    public static Contact contactForSave() {
        return new Contact(
                null,
                "+23432534253",
                "email@gmail.com"
        );
    }

    public static Address addressForSave() {
        return new Address(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                "1",
                null,
                null,
                "49000"
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

    public static ProfileDto profile() {
        return new ProfileDto(
                1L,
                "Vasia",
                "Kolia",
                "Pupkin",
                "14.22.22",
                Sex.man
        );
    }

    public static ContactDto contact() {
        return new ContactDto(
                1L,
                "+23432534253",
                "email@gmail.com"
        );
    }

    public static AddressDto address() {
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


    public static ProfileDto profile1() {
        return new ProfileDto(
                1L,
                "default",
                "default",
                "default",
                "default",
                Sex.unknown
        );
    }

    public static ContactDto contact1() {
        return new ContactDto(
                1L,
                "default",
                "default"
        );
    }

    public static AddressDto address1() {
        return new AddressDto(
                1L,
                "default",
                "default",
                "default",
                "default",
                "default",
                "default",
                "default"
        );
    }

    public static final List<ProductDto> VIEWS_PRODUCT = Lists.newArrayList(
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
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    4L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    5L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    6L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    7L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    8L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    9L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    10L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    11L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    12L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    13L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    14L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    15L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    16L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    17L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    18L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    19L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    20L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    21L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    22L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    23L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    24L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    25L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    26L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    27L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    28L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    29L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    30L,
                    "Brand3",
                    "Sumsung",
                    new BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    Lists.newArrayList("1", "2", "3"),
                    SPECIFICATIONS,
                    COMMENTS,
                    "subcategory1",
                    0
            ),
            new ProductDto(
                    31L,
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
            new ProductDto(
                    32L,
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
            )
    );

    public static List<View> VIEWS_FOR_SAVE = Lists.newArrayList(
            new View(1L),
            new View(2L),
            new View(3L),
            new View(4L),
            new View(5L),
            new View(6L),
            new View(7L),
            new View(8L),
            new View(9L),
            new View(10L),
            new View(11L),
            new View(12L),
            new View(13L),
            new View(14L),
            new View(15L),
            new View(16L),
            new View(17L),
            new View(18L),
            new View(19L),
            new View(20L),
            new View(21L),
            new View(22L),
            new View(23L),
            new View(24L),
            new View(25L),
            new View(26L),
            new View(27L),
            new View(28L),
            new View(29L),
            new View(30L),
            new View(31L),
            new View(32L)
    );

}
