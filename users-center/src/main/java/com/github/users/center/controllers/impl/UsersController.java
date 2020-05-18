package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IUsersController;
import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.PassResetToken;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.exceptions.Conflict;
import com.github.users.center.exceptions.PreconditionFailed;
import com.github.users.center.payload.ConfirmEmail;
import com.github.users.center.payload.JwtAuthResponse;
import com.github.users.center.services.IConfirmService;
import com.github.users.center.services.IEmailService;
import com.github.users.center.services.IResetPassService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.Logging;
import com.github.users.center.utils.UsersUtils;
import com.github.users.center.utils.JwtTokenProvider;
import com.github.users.center.utils.TransferObj;
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
import static com.github.users.center.utils.UsersUtils.*;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class UsersController implements IUsersController, Serializable {

    private static final long serialVersionUID = -8942247909257790435L;

    private final IUserService us;

    private final IConfirmService cs;

    private final IResetPassService rps;

    private final PasswordEncoder pe;

    private final JwtTokenProvider jwtTokenProvider;

    private final IEmailService es;

    @Override
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<Void>
    submitReg(String userUrl, @Valid UserRegDto payload) {
        if (this.us.existsByEmailOrLogin(payload.getEmail(), payload.getLogin())) {
            throw new Conflict();
        }
        var user = TransferObj.user(payload, ROLE_USER);
        user.setPass(this.pe.encode(user.getPass()));
        this.us.create(user);
        var ct = new ConfirmToken(userUrl, user);
        this.cs.create(ct);
        ConfirmEmail cf = fetchConfirmEmail(ct.getToken(), user);
        this.es.submitReg(cf);
        return new ResponseEntity<>(CREATED);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<Void> confirmAccount(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BadRequest();
        }
        ConfirmToken ct = this.cs.readByToken(token);
        this.us.updateIsEnable(TRUE, ct.getUser().getId());
        var url = UsersUtils.createUri(ct.getUserUrl());
        if (Objects.isNull(url)) {
            return new ResponseEntity<>(OK);
        }
        var headers = new HttpHeaders();
        headers.setLocation(url);
        return new ResponseEntity<>(headers, SEE_OTHER);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<JwtAuthResponse> submitAuth(@Valid UserAuthDto payload) {
        var userName = payload.getUserName();
        var pass = payload.getPass();
        var user = this.us.readByEmailOrLogin(userName, userName);
        if (this.pe.matches(pass, user.getPass()) && user.isEnable()) {
            final String token = this.jwtTokenProvider.createUserAccessToken(user);
            var response = new JwtAuthResponse(TYPE_HTTP_TOKEN, token);
            return new ResponseEntity<>(response, OK);
        }
        return new ResponseEntity<>(UNAUTHORIZED);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<Void> processForgotPass(@Valid ForgotPassDto payload) {
        var user = this.us.readByEmail(payload.getEmail());
        var rt = new PassResetToken(user);
        rt.setNewPass(this.pe.encode(payload.getPass()));
        rt.setExpiryDate(EXPIRATION_TIME);
        this.rps.create(rt);
        ConfirmEmail cf = fetchConfirmEmail(rt.getToken(), user);
        this.es.resetPass(cf);
        return new ResponseEntity<>(OK);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<Void> resetPass(String token) {
        PassResetToken result = this.rps.readByToken(token);
        if (result.isExpired()) {
            throw new PreconditionFailed();
        }
        var user = result.getUser();
        this.us.updatePass(result.getNewPass(), user.getId());
        this.rps.delete(result);
        return new ResponseEntity<>(OK);
    }

}
