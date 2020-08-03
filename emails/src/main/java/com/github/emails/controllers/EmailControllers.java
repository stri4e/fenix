package com.github.emails.controllers;

import com.github.emails.payload.ConfirmEmail;
import com.github.emails.payload.LoginNotification;
import com.github.emails.services.LoginNotificationService;
import com.github.emails.services.ResetPassService;
import com.github.emails.services.SubmitRegService;
import com.github.emails.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class EmailControllers implements IEmailController {

    private final SubmitRegService submitReg;

    private final ResetPassService resetPass;

    private final LoginNotificationService loginNotification;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void submitReg(@Valid @RequestBody ConfirmEmail payload) {
        this.submitReg.send(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void resetPass(@Valid @RequestBody ConfirmEmail payload) {
        this.resetPass.send(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void loginNotification(@Valid LoginNotification payload) {
        this.loginNotification.send(payload);
    }

}
