package com.github.orders.controllers.impl;

import com.github.orders.dto.CustomerDto;
import com.github.orders.entity.Customer;

import java.util.UUID;

public class CustomerControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

   public static Customer customerForSave() {
       return new Customer(
               null,
               "Vasia",
               "Utkin",
               "Dnepr Pukina street 45",
               "+7832184214",
               USER_ID
       );
   }

    public static CustomerDto customerForEquals() {
        return new CustomerDto(
                1L,
                "Vasia",
                "Utkin",
                "Dnepr Pukina street 45",
                "+7832184214"
        );
    }

    public static CustomerDto customerForUpdate() {
        return new CustomerDto(
                1L,
                "Vasia",
                "Zubkin",
                "Dnepr Pukina street 45",
                "+7832184214"
        );
    }

}
