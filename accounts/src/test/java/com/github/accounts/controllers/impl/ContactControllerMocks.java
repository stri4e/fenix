package com.github.accounts.controllers.impl;

import com.github.accounts.dto.ContactDto;
import com.github.accounts.entity.Contact;

public class ContactControllerMocks {

    public static Contact contactForSave() {
        return new Contact(
                null,
                "+23432534253",
                "email@gmail.com"
        );
    }

    public static ContactDto response() {
        return new ContactDto(
                1L,
                "+23432534253",
                "email@gmail.com"
        );
    }

    public static ContactDto request() {
        return new ContactDto(
                null,
                "+23432534253",
                "email@gmail.com"
        );
    }

    public static ContactDto requestForUpdate() {
        return new ContactDto(
                null,
                "+555678842",
                "email@gmail.com"
        );
    }

}
