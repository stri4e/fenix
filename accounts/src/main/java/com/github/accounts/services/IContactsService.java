package com.github.accounts.services;

import com.github.accounts.entity.Contact;

public interface IContactsService {

    Contact create(Contact contact);

    Contact readById(Long id);

    void update(Long id, String phone, String email);

    void remove(Long id);

}
