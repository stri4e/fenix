package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IUsersController;
import com.github.users.center.dto.*;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.User;
import com.github.users.center.exceptions.Conflict;
import com.github.users.center.exceptions.Unauthorized;
import com.github.users.center.payload.EmailNotification;
import com.github.users.center.payload.JwtAuthResponse;
import com.github.users.center.services.*;
import com.github.users.center.utils.JwtTokenProvider;
import com.github.users.center.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.users.center.dto.AccountDto.accountDef;
import static com.github.users.center.dto.ClientDto.client;
import static com.github.users.center.utils.TransferObj.toUser;
import static com.github.users.center.utils.UsersUtils.ROLE_USER;
import static java.util.concurrent.CompletableFuture.runAsync;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class UsersController implements IUsersController {

    private final IUserService userService;

    private final IConfirmService confirmService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final IEmailService emailService;

    private final ILoginsService loginsService;

    private final IAccountsService accountsService;

    private final IClientService clientService;

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
            runAsync(() -> this.registration(user, origin, ct));
            runAsync(() -> this.accountsService.createAccount(
                    user.getId(), accountDef(user)
            ));
            runAsync(() -> this.clientService.create(client(user)));
        } else {
            throw new Conflict();
        }
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public JwtAuthResponse
    submitAuth(String ip, String userAgent, @Valid UserAuthDto payload) {
        var userName = payload.getUserName();
        User user = this.userService.readByEmailOrLogin(userName, userName);
        if (user.isAuth(pass -> this.passwordEncoder.matches(payload.getPass(), pass))) {
            var token = this.jwtTokenProvider.userAccessToken(user);
            runAsync(() -> logins(token, user, ip, userAgent));
            return new JwtAuthResponse(token);
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
