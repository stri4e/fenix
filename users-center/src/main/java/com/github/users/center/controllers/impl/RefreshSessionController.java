package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.IRefreshSessionController;
import com.github.users.center.entity.EntityStatus;
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

import static com.github.users.center.utils.UsersUtils.ADMIN_SCOPE;
import static com.github.users.center.utils.UsersUtils.MANAGER_SCOPE;

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
            RefreshSession session = this.refreshSessionService.readActiveByUserId(userId);
            if (!session.isExpired()) {
                User user = this.userService.readById(userId);
                var accessToken = this.jwtTokenProvider.adminAccessToken(user);
                RefreshSession newSession = this.jwtTokenProvider
                        .refreshAdminSession(fingerprint, session.getIp(), user, ADMIN_SCOPE);
                this.refreshSessionService.update(session.getId(), EntityStatus.off);
                this.refreshSessionService.create(newSession);
                return new JwtRefreshResponse(accessToken, newSession.getRefreshToken(), newSession.expireIn());
            }
            this.refreshSessionService.update(session.getId(), EntityStatus.off);
        }
        throw new Unauthorized();
    }

    @Override
    public JwtRefreshResponse refreshSessionManagers(String refreshToken) {
        if (this.jwtTokenProvider.validateRefreshToken(refreshToken)) {
            var userId = this.jwtTokenProvider.fetchUser(refreshToken);
            var fingerprint = this.jwtTokenProvider.fetchFingerprint(refreshToken);
            RefreshSession session = this.refreshSessionService.readActiveByUserId(userId);
            if (!session.isExpired()) {
                User user = this.userService.readById(userId);
                var accessToken = this.jwtTokenProvider.managerAccessToken(user);
                RefreshSession newSession = this.jwtTokenProvider
                        .refreshManagerSession(fingerprint, session.getIp(), user, MANAGER_SCOPE);
                this.refreshSessionService.update(session.getId(), EntityStatus.off);
                this.refreshSessionService.create(newSession);
                return new JwtRefreshResponse(accessToken, newSession.getRefreshToken(), newSession.expireIn());
            }
            this.refreshSessionService.update(session.getId(), EntityStatus.off);
        }
        throw new Unauthorized();
    }

}
