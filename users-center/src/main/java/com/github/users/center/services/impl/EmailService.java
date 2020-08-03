package com.github.users.center.services.impl;

import com.github.users.center.payload.ConfirmEmail;
import com.github.users.center.payload.LoginNotification;
import com.github.users.center.services.IEmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Override
    public void submitReg(ConfirmEmail payload) {
    }

    @Override
    public void resetPass(ConfirmEmail payload) {
    }

    @Override
    public void loginNotification(LoginNotification payload) {
    }

}
