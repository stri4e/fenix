package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IAdminController;
import com.github.users.center.dto.LoginDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.RefreshSession;
import com.github.users.center.entity.User;
import com.github.users.center.exceptions.Conflict;
import com.github.users.center.exceptions.Unauthorized;
import com.github.users.center.payload.EmailNotification;
import com.github.users.center.payload.JwtRefreshResponse;
import com.github.users.center.services.*;
import com.github.users.center.utils.JwtTokenProvider;
import com.github.users.center.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

import static com.github.users.center.utils.TransferObj.toUser;
import static com.github.users.center.utils.UsersUtils.ADMIN_SCOPE;
import static com.github.users.center.utils.UsersUtils.ROLE_ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/admins")
public class AdminController implements IAdminController {

    private final IUserService userService;

    private final IConfirmService confirmService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final IRefreshSessionService refreshSessionService;

    private final IEmailService emailService;

    private final ILoginsService loginsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void submitReg(String origin, @Valid UserRegDto payload) {
        if (!this.userService.existsByEmailOrLogin(payload.getEmail(), payload.getLogin())) {
            User user = toUser(payload, ROLE_ADMIN);
            user.setPass(this.passwordEncoder.encode(user.getPass()));
            this.userService.create(user);
            ConfirmToken ct = new ConfirmToken(user);
            this.confirmService.create(ct);
            CompletableFuture.runAsync(() -> registration(user, origin, ct));
        } else {
            throw new Conflict();
        }
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public JwtRefreshResponse
    submitAuth(String ip, String fingerprint, String userAgent, @Valid UserAuthDto payload) {
        var userName = payload.getUserName();
        User user = this.userService.readByEmailOrLogin(userName, userName);
        if (user.isAuth(pass -> this.passwordEncoder.matches(payload.getPass(), pass))) {
            var accessToken = this.jwtTokenProvider.adminAccessToken(user);
            RefreshSession rs = this.jwtTokenProvider
                    .refreshAdminSession(fingerprint, ip, user, ADMIN_SCOPE);
            RefreshSession session = this.refreshSessionService.create(rs);
            CompletableFuture.runAsync(() -> logins(accessToken, user, ip, userAgent));
            return new JwtRefreshResponse(accessToken, session.getRefreshToken(), session.expireIn());
        }
        throw new Unauthorized();
    }

    private void registration(User user, String origin, ConfirmToken ct) {
        EmailNotification notification = EmailNotification.userChangeNotify(
                user.getEmail(), user.getFName(), user.getLName(),
                origin, "/emails/v1/pages/confirm-account/", ct.getToken()
        );
        this.emailService.submitReg(notification);
    }

    private void logins(String token, User user, String ip, String userAgent) {
        EmailNotification notification = EmailNotification.loginNotify(
                user.getEmail(), ip, userAgent, user.getFName()
        );
        this.emailService.loginNotification(notification);
        LoginDto login = new LoginDto(token, ip, notification.getInformation());
        this.loginsService.createLogin(login);
    }

}
