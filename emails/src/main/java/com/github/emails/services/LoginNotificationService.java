package com.github.emails.services;

import com.github.emails.models.Mail;
import com.github.emails.payload.LoginNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginNotificationService {

    private static final String
            LOGIN_NOTIFICATION_REQUEST = "Login notification.";

    private static final String
            TEMPLATE_NAME = "notification-login.ftl";

    private final EmailService emailService;

    public void send(LoginNotification notification) {
        var mail = new Mail(
                notification.getEmail(),
                "todo.list@gmail.com",
                LOGIN_NOTIFICATION_REQUEST,
                notification.getInformation()
        );
        this.emailService.sendEmail(mail, TEMPLATE_NAME);
    }

}
