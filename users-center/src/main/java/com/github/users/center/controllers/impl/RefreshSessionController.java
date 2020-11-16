package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IRefreshSessionController;
import com.github.users.center.entity.RefreshSession;
import com.github.users.center.entity.User;
import com.github.users.center.exceptions.Unauthorized;
import com.github.users.center.payload.JwtRefreshResponse;
import com.github.users.center.services.IRefreshSessionService;
import com.github.users.center.services.IUserService;
import com.github.users.center.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

import static com.github.users.center.utils.UsersUtils.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/refresh/session")
public class RefreshSessionController implements IRefreshSessionController {

    private final IUserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    private final IRefreshSessionService refreshSessionService;

    @Override
    public JwtRefreshResponse refreshSessionAdmins(@Valid String refreshToken) {
        if (this.jwtTokenProvider.validateRefreshToken(refreshToken)) {
            var userId = this.jwtTokenProvider.fetchUser(refreshToken);
            var fingerprint = this.jwtTokenProvider.fetchFingerprint(refreshToken);
            List<RefreshSession> sessions = this.refreshSessionService.readAllByUserId(userId);
            RefreshSession session = findSession(sessions, fingerprint);
            if (!session.isExpired()) {
                User user = this.userService.readById(userId);
                var accessToken = this.jwtTokenProvider.adminAccessToken(user);
                RefreshSession newSession = this.jwtTokenProvider
                        .refreshAdminSession(fingerprint, session.getIp(), user, ADMIN_SCOPE);
                return jwtRefreshResponse(session, accessToken, newSession);
            }
        }
        throw new Unauthorized();
    }

    @Override
    public JwtRefreshResponse refreshSessionManagers(String refreshToken) {
        if (this.jwtTokenProvider.validateRefreshToken(refreshToken)) {
            var userId = this.jwtTokenProvider.fetchUser(refreshToken);
            var fingerprint = this.jwtTokenProvider.fetchFingerprint(refreshToken);
            List<RefreshSession> sessions = this.refreshSessionService.readAllByUserId(userId);
            RefreshSession session = findSession(sessions, fingerprint);
            if (!session.isExpired()) {
                User user = this.userService.readById(userId);
                var accessToken = this.jwtTokenProvider.managerAccessToken(user);
                RefreshSession newSession = this.jwtTokenProvider
                        .refreshManagerSession(fingerprint, session.getIp(), user, MANAGER_SCOPE);
                return jwtRefreshResponse(session, accessToken, newSession);
            }
        }
        throw new Unauthorized();
    }

    private RefreshSession findSession(List<RefreshSession> rss, String fingerprint) {
        RefreshSession session;
        if (rss.size() > MAX_REFRESH_SESSION) {
            session = rss.stream()
                    .max(Comparator.comparing(RefreshSession::getExpireIn))
                    .filter(f -> fingerprint.equals(f.getFingerprint()))
                    .orElseThrow(Unauthorized::new);
            rss.stream()
                    .filter(s -> !s.equals(session))
                    .forEach(s -> this.refreshSessionService.remove(s.getId()));
        } else {
            session = rss.stream()
                    .filter(f -> fingerprint.equals(f.getFingerprint()))
                    .findFirst().orElseThrow(Unauthorized::new);
        }
        return session;
    }

    private JwtRefreshResponse
    jwtRefreshResponse(RefreshSession session, String accessToken, RefreshSession newSession) {
        this.refreshSessionService.remove(session.getId());
        this.refreshSessionService.create(newSession);
        return new JwtRefreshResponse(
                accessToken,
                newSession.getRefreshToken(),
                newSession.expireIn()
        );
    }

}
