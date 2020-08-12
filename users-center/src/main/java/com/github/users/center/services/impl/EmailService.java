package com.github.users.center.services.impl;

import com.github.users.center.payload.EmailNotification;
import com.github.users.center.services.IEmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {


    @Override
    public void submitReg(EmailNotification payload) {

    }

    @Override
    public void resetPass(EmailNotification payload) {

    }

    @Override
    public void loginNotification(EmailNotification payload) {
    }

}
