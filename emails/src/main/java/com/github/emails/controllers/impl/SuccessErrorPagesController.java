package com.github.emails.controllers.impl;

import com.github.emails.controllers.ISuccessErrorPagesController;
import com.github.emails.payload.RenderTemplate;
import com.github.emails.utils.ConfirmReport;
import com.github.emails.services.IUsersCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/v1/pages")
public class SuccessErrorPagesController implements ISuccessErrorPagesController {

    private final IUsersCenterService usersCenterService;

    @Override
    public String confirmAccount(String token) {
        return this.usersCenterService.confirmAccount(token)
                .orElse(RenderTemplate.errorDef())
                .renderConfirmAccount();
    }

    @Override
    public String resetPass(String token) {
        return this.usersCenterService.resetPassword(token)
                .orElse(RenderTemplate.errorDef())
                .renderResetPass();
    }

}
