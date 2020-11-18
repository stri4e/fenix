package com.github.accounts.services.impl;

import com.github.accounts.entity.Contact;
import com.github.accounts.entity.EntityStatus;
import com.github.accounts.exceptions.NotFound;
import com.github.accounts.repository.ContactsRepo;
import com.github.accounts.services.IContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactsService implements IContactsService {

    private final ContactsRepo contactsRepo;

    @Override
    public Contact create(Contact contact) {
        return this.contactsRepo.save(contact);
    }

    @Override
    public Contact readById(Long id) {
        return this.contactsRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String phone, String email) {
        this.contactsRepo.update(id, phone, email);
    }

    @Override
    public void remove(Long id) {
        this.contactsRepo.delete(id, EntityStatus.off);
    }
}
