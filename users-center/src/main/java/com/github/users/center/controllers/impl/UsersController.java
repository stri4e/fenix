package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IUsersController;
import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.LoginDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.UserAlias;
import com.github.users.center.entity.PassResetToken;
import com.github.users.center.entity.User;
import com.github.users.center.exceptions.Conflict;
import com.github.users.center.exceptions.Unauthorized;
import com.github.users.center.payload.EmailNotification;
import com.github.users.center.payload.JwtAuthResponse;
import com.github.users.center.payload.RenderTemplate;
import com.github.users.center.services.*;
import com.github.users.center.utils.JwtTokenProvider;
import com.github.users.center.utils.Logging;
import com.github.users.center.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.github.users.center.utils.TransferObj.toUser;
import static com.github.users.center.utils.UsersUtils.EXPIRATION_TIME;
import static com.github.users.center.utils.UsersUtils.ROLE_USER;
import static java.lang.Boolean.TRUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class UsersController implements IUsersController {

    private final IUserService userService;

    private final IConfirmService confirmService;

    private final IResetPassService resetPassService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final IEmailService emailService;

    private final ILoginsService loginsService;

    private final IUserAliasService notificationService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void submitReg(String origin, @Valid UserRegDto payload) {
        if (!this.userService.existsByEmailOrLogin(payload.getEmail(), payload.getLogin())) {
            User user = toUser(payload, ROLE_USER);
            user.setPass(this.passwordEncoder.encode(user.getPass()));
            this.userService.create(user);
            ConfirmToken ct = new ConfirmToken(user);
            this.confirmService.create(ct);
            CompletableFuture.runAsync(() -> this.registration(user, origin, ct));
        }
        throw new Conflict();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public RenderTemplate confirmAccount(String token) {
        ConfirmToken ct = this.confirmService.readByToken(token);
        User user = ct.getUser();
        this.userService.updateIsEnable(TRUE, user.getId());
        var prefix = UUID.randomUUID().toString();
        UserAlias notify = new UserAlias(user, prefix);
        this.notificationService.save(notify);
        return RenderTemplate.success();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public JwtAuthResponse
    submitAuth(String ip, String userAgent, @Valid UserAuthDto payload) {
        var userName = payload.getUserName();
        var pass = payload.getPass();
        User user = this.userService.readByEmailOrLogin(userName, userName);
        if (this.passwordEncoder.matches(pass, user.getPass()) && user.isEnable()) {
            var token = this.jwtTokenProvider.userAccessToken(user);
            CompletableFuture.runAsync(() -> logins(user, ip, userAgent));
            return new JwtAuthResponse(token);
        }
        throw new Unauthorized();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void processForgotPass(String origin, @Valid ForgotPassDto payload) {
        User user = this.userService.readByEmail(payload.getEmail());
        PassResetToken rt = new PassResetToken(user);
        rt.setNewPass(this.passwordEncoder.encode(payload.getPass()));
        rt.setExpiryDate(EXPIRATION_TIME);
        this.resetPassService.create(rt);
        CompletableFuture.runAsync(() -> this.forgotPass(user, origin, rt));
    }

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

    private void registration(User user, String origin, ConfirmToken ct) {
        EmailNotification notification = EmailNotification.userChangeNotify(
                user.getEmail(), user.getFName(), user.getLName(),
                origin, "/emails/v1/pages/confirm-account/", ct.getToken()
        );
        this.emailService.submitReg(notification);
    }

    private void logins(User user, String ip, String userAgent) {
        EmailNotification notification = EmailNotification.loginNotify(
                user.getEmail(), ip, userAgent, user.getFName()
        );
        this.emailService.loginNotification(notification);
        LoginDto login = new LoginDto(user.getId(), userAgent, ip);
        this.loginsService.createLogin(login);
    }

    private void forgotPass(User user, String origin, PassResetToken rt) {
        EmailNotification notification = EmailNotification.userChangeNotify(
                user.getEmail(), user.getFName(), user.getLName(),
                origin, "/emails/v1/pages/reset-pass/", rt.getToken()
        );
        this.emailService.resetPass(notification);
    }

}
