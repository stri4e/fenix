package com.github.emails.services;

import com.github.emails.payload.ConfirmEmail;
import com.github.emails.models.Mail;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

import static com.github.emails.utils.EmailConstants.gatewayService;
import static com.github.emails.utils.EmailsModels.createModel;


@Slf4j
@Component
@RequiredArgsConstructor
public class SubmitRegService {

    private static final String
            ENABLE_ACCOUNT_MESSAGE = "Confirmation enable account";

    private static final String
            TEMPLATE_NAME = "confirmation-email-template.ftl";

    @Value("${app.submitReg}")
    private String urlSubmitReg;

    private final EmailService emailService;

    @Qualifier("eurekaClient")
    private final EurekaClient eurekaClient;

    public void send(ConfirmEmail args) {
        var url = getUri(args.getToken());
        var model = createModel(
                args.getFirstName(),
                args.getLastName(),
                url
        );
        var mail = createMail(args, model);
        this.emailService.sendEmail(mail, TEMPLATE_NAME);
    }

    private Mail
    createMail(ConfirmEmail data, Map<String, Object> model ) {
        return new Mail(
                data.getEmail(),
                "todo.list@gmail.com",
                ENABLE_ACCOUNT_MESSAGE,
                model
        );
    }

    private String getUri(final String token) {
        InstanceInfo instanceInfo = this.eurekaClient
                .getNextServerFromEureka(
                        gatewayService.getInfo(),
                        Boolean.FALSE
                );
        var uri = URI.create(instanceInfo.getHomePageUrl());
        return UriComponentsBuilder.newInstance()
                .uri(uri).path(this.urlSubmitReg)
                .queryParam("token", token)
                .build().toString();
    }

}
