package com.github.accounts.controllers.impl;

import com.github.accounts.entity.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ViewsControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static Account accountForSave(Profile profile, Contact contact, Address address) {
        return new Account(
                USER_ID,
                profile,
                contact,
                address,
                new HashSet<>()
        );
    }

    public static Account accountForSave(Profile profile, Contact contact, Address address, View view) {
        return new Account(
                USER_ID,
                profile,
                contact,
                address,
                Set.of(view)
        );
    }

    public static Address addressForSave() {
        return new Address(
                null,
                "Ukraina",
                "Dnepr",
                "Julvern",
                1,
                null,
                null,
                49000
        );
    }

    public static Contact contactForSave() {
        return new Contact(
                null,
                "+23432534253",
                "email@gmail.com"
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

    public static View viewForSave() {
        return new View(1L);
    }

}
