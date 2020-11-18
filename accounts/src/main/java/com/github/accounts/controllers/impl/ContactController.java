package com.github.accounts.controllers.impl;

import com.github.accounts.controllers.IContactController;
import com.github.accounts.dto.ContactDto;
import com.github.accounts.services.IContactsService;
import com.github.accounts.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.accounts.utils.TransferObj.fromContact;
import static com.github.accounts.utils.TransferObj.toContact;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/contacts")
public class ContactController implements IContactController {

    private final IContactsService contactsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ContactDto findContact(Long contactId) {
        return fromContact(this.contactsService.readById(contactId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ContactDto save(ContactDto payload) {
        return fromContact(this.contactsService.create(toContact(payload)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(ContactDto payload) {
         this.contactsService.update(
                 payload.getId(),
                 payload.getPhones(),
                 payload.getEmail()
         );
    }

}
