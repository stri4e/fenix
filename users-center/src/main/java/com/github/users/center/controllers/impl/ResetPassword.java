package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IResetPassword;
import com.github.users.center.entity.PassResetToken;
import com.github.users.center.entity.User;
import com.github.users.center.payload.RenderTemplate;
import com.github.users.center.services.IResetPassService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/reset-pass")
public class ResetPassword implements IResetPassword {

    private final IUserService userService;

    private final IResetPassService resetPassService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public RenderTemplate resetPass(String token) {
        PassResetToken prt = this.resetPassService.readByToken(token);
        if (prt.isExpired()) {
            return RenderTemplate.error("Token is expired.");
        }
        User user = prt.getUser();
        this.userService.updatePass(prt.getNewPass(), user.getId());
        this.resetPassService.delete(prt);
        return RenderTemplate.success();
    }

}
