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
import com.github.users.center.services.IEmailService;
import com.github.users.center.services.IRefreshSessionService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.JwtTokenProvider;
import com.github.users.center.utils.Logging;
import com.github.users.center.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/admins")
public class AdminController implements IAdminController, Serializable {

    private final static long serialVersionUID = 5551087240799808634L;

    private final IUserService userService;

    private final IConfirmService confirmService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final IRefreshSessionService refreshSessionService;

    private final IEmailService emailService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void submitReg(String userUrl, @Valid UserRegDto payload) {
        if (this.userService.existsByEmailOrLogin(payload.getEmail(), payload.getLogin())) {
            throw new Conflict();
        }
        var user = TransferObj.user(payload, ROLE_ADMIN);
        user.setPass(this.passwordEncoder.encode(user.getPass()));
        this.userService.create(user);
        var ct = new ConfirmToken(userUrl, user);
        this.confirmService.create(ct);
        ConfirmEmail cf = fetchConfirmEmail(ct.getToken(), user);
        this.emailService.submitReg(cf);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public JwtRefreshResponse
    submitAuth(String fingerprint, String address, @Valid UserAuthDto payload) {
        var userName = payload.getUserName();
        var pass = payload.getPass();
        var user = this.userService.readByEmailOrLogin(userName, userName);
        if (this.passwordEncoder.matches(pass, user.getPass()) && user.isEnable()) {
            var accessToken = this.jwtTokenProvider.createAdminAccessToken(user);
            RefreshSession rs = this.jwtTokenProvider.createRefreshSession(
                    fingerprint, address, user
            );
            this.refreshSessionService.create(rs);
            return new JwtRefreshResponse(
                    TYPE_HTTP_TOKEN,
                    accessToken,
                    rs.getRefreshToken(),
                    this.jwtTokenProvider.getRefreshExpireTime()
            );
        }
        throw new Unauthorized();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public JwtRefreshResponse
    submitRefreshSession(@Valid String refreshToken) {
        if (this.jwtTokenProvider.validateRefreshToken(refreshToken)) {
            var userId = this.jwtTokenProvider.getUserFromJwt(refreshToken);
            var fingerprint = this.jwtTokenProvider.getFingerprintFromJwt(refreshToken);
            List<RefreshSession> sessions = this.refreshSessionService.readAllByUserId(userId);
            RefreshSession session = findSession(sessions, fingerprint);
            if (!session.isExpired()) {
                User user = this.userService.readById(userId);
                var accessToken = this.jwtTokenProvider.createAdminAccessToken(user);
                RefreshSession newSession = this.jwtTokenProvider.createRefreshSession(
                        fingerprint, session.getIp(), user
                );
                this.refreshSessionService.remove(session.getId());
                this.refreshSessionService.create(newSession);
                return new JwtRefreshResponse(
                        TokenType.TYPE_HTTP_TOKEN,
                        accessToken,
                        newSession.getRefreshToken(),
                        this.jwtTokenProvider.getRefreshExpireTime()
                );
            }
        }
        throw new Unauthorized();
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
            rss.stream().filter(sp).forEach(s -> this.refreshSessionService.remove(s.getId()));
        } else {
            session = rss.stream()
                    .filter(fp)
                    .findFirst()
                    .orElseThrow(Unauthorized::new);
        }
        return session;
    }

}
