package com.github.emails.controllers.impl;

import com.github.emails.controllers.ISuccessErrorPagesController;
import com.github.emails.payload.ConfirmReport;
import com.github.emails.services.IUsersCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/pages")
public class SuccessErrorPagesController implements ISuccessErrorPagesController {

    private final IUsersCenterService usersCenterService;

    @Override
    public String confirmAccount(String token) {
        ConfirmReport report = this.usersCenterService.confirmAccount(token)
                .orElse(ConfirmReport.error);
        return report.confirmAccount();
    }

    @Override
    public String resetPass(String token) {
        ConfirmReport report = this.usersCenterService.resetPassword(token)
                .orElse(ConfirmReport.error);
        return report.resetPass();
    }

}
