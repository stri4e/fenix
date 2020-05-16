package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IAdminController;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.RefreshSession;
import com.github.users.center.entity.User;
import com.github.users.center.exceptions.Conflict;
import com.github.users.center.exceptions.Unauthorized;
import com.github.users.center.payload.ConfirmEmail;
import com.github.users.center.payload.JwtRefreshResponse;
import com.github.users.center.payload.TokenType;
import com.github.users.center.services.IConfirmService;
import com.github.users.center.services.IRefreshSessionService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.JwtTokenProvider;
import com.github.users.center.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static com.github.users.center.payload.TokenType.TYPE_HTTP_TOKEN;
import static com.github.users.center.utils.UsersUtils.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/admins")
public class AdminController implements IAdminController, Serializable {

    private final static long serialVersionUID = 5551087240799808634L;

    private final IUserService us;

    private final IConfirmService cs;

    private final PasswordEncoder pe;

    private final JwtTokenProvider jtp;

    private final IRefreshSessionService rss;

    @Override
    public ResponseEntity<Void> submitReg(String userUrl, @Valid UserRegDto payload) {
        if (this.us.existsByEmailOrLogin(
                payload.getEmail(), payload.getLogin())) {
            throw new Conflict();
        }
        var user = TransferObj.user(payload, ROLE_ADMIN);
        user.setPass(this.pe.encode(user.getPass()));
        this.us.create(user);
        var ct = new ConfirmToken(userUrl, user);
        this.cs.create(ct);
        ConfirmEmail cf = fetchConfirmEmail(ct.getToken(), user);
        //todo send email to email service
        return new ResponseEntity<>(CREATED);
    }

    @Override
    public ResponseEntity<JwtRefreshResponse>
    submitAuth(String fingerprint, String address, @Valid UserAuthDto payload) {
        var userName = payload.getUserName();
        var pass = payload.getPass();
        var user = this.us.readByEmailOrLogin(userName, userName);
        if (this.pe.matches(pass, user.getPass()) && user.isEnable()) {
            var accessToken = this.jtp.createAdminAccessToken(user);
            RefreshSession rs = this.jtp.createRefreshSession(
                    fingerprint, address, user
            );
            this.rss.create(rs);
            var response = new JwtRefreshResponse(
                    TYPE_HTTP_TOKEN,
                    accessToken,
                    rs.getRefreshToken(),
                    this.jtp.getRefreshExpireTime()
            );
            return new ResponseEntity<>(response, OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<JwtRefreshResponse>
    submitRefreshSession(String refreshToken, @Valid String fingerprint) {
        if (this.jtp.validateRefreshToken(refreshToken)) {
            var userId = this.jtp.getUserFromJwt(refreshToken);
            var sessions = this.rss.readAllByUserId(userId);
            RefreshSession session = findSession(sessions, fingerprint);
            if (!session.isExpired()) {
                var user = this.us.readById(userId);
                var accessToken = this.jtp.createAdminAccessToken(user);
                RefreshSession newSession = this.jtp.createRefreshSession(
                        fingerprint, session.getIp(), user
                );
                this.rss.remove(session.getId());
                this.rss.create(newSession);
                var response = new JwtRefreshResponse(
                        TokenType.TYPE_HTTP_TOKEN,
                        accessToken,
                        newSession.getRefreshToken(),
                        this.jtp.getRefreshExpireTime()
                );
                return new ResponseEntity<>(response, CREATED);
            }
        }
        return new ResponseEntity<>(UNAUTHORIZED);
    }

    private RefreshSession findSession(List<RefreshSession> rss, String fingerprint) {
        Predicate<RefreshSession> fp = f -> fingerprint.equals(f.getFingerprint());
        RefreshSession session;
        if (rss.size() > MAX_REFRESH_SESSION) {
            session = rss.stream()
                    .max(Comparator.comparing(RefreshSession::getExpireIn))
                    .filter(fp)
                    .orElseThrow(Unauthorized::new);
            Predicate<RefreshSession> sp = s -> !s.equals(session);
            rss.stream().filter(sp).forEach(s -> this.rss.remove(s.getId()));
        } else {
            session = rss.stream()
                    .filter(fp)
                    .findFirst()
                    .orElseThrow(Unauthorized::new);
        }
        return session;
    }

}
