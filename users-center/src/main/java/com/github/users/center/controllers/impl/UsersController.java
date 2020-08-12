package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IUsersController;
import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.PassResetToken;
import com.github.users.center.entity.User;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.exceptions.Conflict;
import com.github.users.center.exceptions.PreconditionFailed;
import com.github.users.center.exceptions.Unauthorized;
import com.github.users.center.payload.EmailNotification;
import com.github.users.center.payload.JwtAuthResponse;
import com.github.users.center.services.IConfirmService;
import com.github.users.center.services.IEmailService;
import com.github.users.center.services.IResetPassService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.JwtTokenProvider;
import com.github.users.center.utils.Logging;
import com.github.users.center.utils.TransferObj;
import com.github.users.center.utils.UsersUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Objects;

import static com.github.users.center.payload.TokenType.TYPE_HTTP_TOKEN;
import static com.github.users.center.utils.UsersUtils.EXPIRATION_TIME;
import static com.github.users.center.utils.UsersUtils.ROLE_USER;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.SEE_OTHER;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class UsersController implements IUsersController, Serializable {

    private static final long serialVersionUID = -8942247909257790435L;

    private final IUserService userService;

    private final IConfirmService confirmService;

    private final IResetPassService resetPassService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final IEmailService emailService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void submitReg(String clientUrl, String prefix, @Valid UserRegDto payload) {
        if (this.userService.existsByEmailOrLogin(payload.getEmail(), payload.getLogin())) {
            throw new Conflict();
        }
        User user = TransferObj.user(payload, ROLE_USER);
        user.setPass(this.passwordEncoder.encode(user.getPass()));
        this.userService.create(user);
        var ct = new ConfirmToken(clientUrl, user);
        this.confirmService.create(ct);
        EmailNotification notification = EmailNotification.userChangeNotify(
                user.getEmail(), user.getFName(), user.getLName(),
                clientUrl, prefix, "/v1/confirm-account", ct.getToken()
        );
        this.emailService.submitReg(notification);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<Void> confirmAccount(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BadRequest();
        }
        ConfirmToken ct = this.confirmService.readByToken(token);
        this.userService.updateIsEnable(TRUE, ct.getUser().getId());
        var url = UsersUtils.createUri(ct.getClientUrl());
        if (Objects.isNull(url)) {
            return new ResponseEntity<>(OK);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(url);
        return new ResponseEntity<>(headers, SEE_OTHER);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public JwtAuthResponse submitAuth(String location, String userInfo, @Valid UserAuthDto payload) {
        var userName = payload.getUserName();
        var pass = payload.getPass();
        User user = this.userService.readByEmailOrLogin(userName, userName);
        if (this.passwordEncoder.matches(pass, user.getPass()) && user.isEnable()) {
            var token = this.jwtTokenProvider.createUserAccessToken(user);
            EmailNotification notification = EmailNotification.loginNotify(
                    user.getEmail(), location, userInfo, user.getFName()
            );
            this.emailService.loginNotification(notification);
            return new JwtAuthResponse(TYPE_HTTP_TOKEN, token);
        }
        throw new Unauthorized();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void processForgotPass(String clientUrl, String prefix, @Valid ForgotPassDto payload) {
        User user = this.userService.readByEmail(payload.getEmail());
        PassResetToken rt = new PassResetToken(user);
        rt.setNewPass(this.passwordEncoder.encode(payload.getPass()));
        rt.setExpiryDate(EXPIRATION_TIME);
        this.resetPassService.create(rt);
        EmailNotification notification = EmailNotification.userChangeNotify(
                user.getEmail(), user.getFName(), user.getLName(),
                clientUrl, prefix, "/v1/reset-pass", rt.getToken()
        );
        this.emailService.resetPass(notification);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void resetPass(String token) {
        PassResetToken result = this.resetPassService.readByToken(token);
        if (result.isExpired()) {
            throw new PreconditionFailed();
        }
        User user = result.getUser();
        this.userService.updatePass(result.getNewPass(), user.getId());
        this.resetPassService.delete(result);
    }

}
