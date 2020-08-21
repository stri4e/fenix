package com.github.emails.controllers;

import com.github.emails.models.Mail;
import com.github.emails.payload.EmailNotification;
import com.github.emails.services.EmailService;
import com.github.emails.utils.Logging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class EmailControllers implements IEmailController {

    private static final String
            SUBMIT_REGISTRATION_MESSAGE = "Submit registration account";

    private static final String
            SUBMIT_TEMPLATE_NAME = "submit-registration-email-template.ftl";

    private static final String
            PASSWORD_RESET_REQUEST = "Password reset request";

    private static final String
            RESET_TEMPLATE_NAME = "forgot-password-email-template.ftl";

    private static final String
            LOGIN_NOTIFICATION_REQUEST = "Login notification.";

    private static final String
            LOGIN_TEMPLATE_NAME = "login-notification-email-template.ftl";

    private final EmailService emailService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public void submitReg(@Valid EmailNotification payload) {
        Mail mail = new Mail(
                payload.getEmail(), "todo.list@gmail.com",
                SUBMIT_REGISTRATION_MESSAGE, payload.getInformation()
        );
        this.emailService.sendEmail(mail, SUBMIT_TEMPLATE_NAME);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void resetPass(@Valid EmailNotification payload) {
        Mail mail = new Mail(
                payload.getEmail(), "todo.list@gmail.com",
                PASSWORD_RESET_REQUEST, payload.getInformation()
        );
        this.emailService.sendEmail(mail, RESET_TEMPLATE_NAME);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void loginNotification(@Valid EmailNotification payload) {
        Mail mail = new Mail(
                payload.getEmail(), "todo.list@gmail.com",
                LOGIN_NOTIFICATION_REQUEST, payload.getInformation()
        );
        this.emailService.sendEmail(mail, LOGIN_TEMPLATE_NAME);
    }

}
