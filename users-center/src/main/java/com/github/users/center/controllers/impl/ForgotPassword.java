package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IForgotPassword;
import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.entity.PassResetToken;
import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;
import com.github.users.center.payload.EmailNotification;
import com.github.users.center.services.IEmailService;
import com.github.users.center.services.IResetPassService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static com.github.users.center.utils.UsersUtils.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/forgot-pass")
public class ForgotPassword implements IForgotPassword {

    private final IUserService userService;

    private final IResetPassService resetPassService;

    private final PasswordEncoder passwordEncoder;

    private final IEmailService emailService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void usersForgotPass(String origin, @Valid ForgotPassDto payload) {
        User user = this.userService.readByEmail(payload.getEmail());
        if (containsRoles(user)) {
            this.userService.updateIsLocked(user.getEmail(), Boolean.TRUE);
        } else {
            var newPass = this.passwordEncoder.encode(payload.getPass());
            PassResetToken rt = PassResetToken.build()
                    .user(user).newPass(newPass)
                    .expiryDate(EXPIRATION_TIME);
            this.resetPassService.create(rt);
            CompletableFuture.runAsync(() -> this.forgotPass(user, origin, rt));
        }
    }

    @Override
    public void staffForgotPass(String origin, @Valid ForgotPassDto payload) {
        User user = this.userService.readByEmail(payload.getEmail());
        var newPass = this.passwordEncoder.encode(payload.getPass());
        PassResetToken rt = PassResetToken.build()
                .user(user).newPass(newPass)
                .expiryDate(EXPIRATION_TIME);
        this.resetPassService.create(rt);
        CompletableFuture.runAsync(() -> this.forgotPass(user, origin, rt));
    }

    private void forgotPass(User user, String origin, PassResetToken rt) {
        EmailNotification notification = EmailNotification.userChangeNotify(
                user.getEmail(), user.getFName(), user.getLName(),
                origin, "/emails/v1/pages/reset-pass/", rt.getToken()
        );
        this.emailService.resetPass(notification);
    }

    private boolean containsRoles(User user) {
        Collection<Role> roles = user.getRoles();
        return roles.stream()
                .map(Role::getRole)
                .anyMatch(r -> ROLE_ADMIN.equals(r) || ROLE_MANAGER.equals(r));
    }

}
