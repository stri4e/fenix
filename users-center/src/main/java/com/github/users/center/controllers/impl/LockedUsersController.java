package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.ILockedUsersController;
import com.github.users.center.dto.LockedDto;
import com.github.users.center.entity.EntityStatus;
import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;
import com.github.users.center.services.IStaffService;
import com.github.users.center.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

import java.util.Collection;

import static com.github.users.center.entity.EntityStatus.choose;
import static com.github.users.center.utils.UsersUtils.ROLE_ADMIN;
import static com.github.users.center.utils.UsersUtils.ROLE_MANAGER;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/locked")
public class LockedUsersController implements ILockedUsersController {

    private final IUserService userService;

    private final IStaffService staffService;

    @Override
    public void lockedUser(LockedDto payload) {
        var email = payload.getEmail();
        var isLocked = payload.isLocked();
        User user = this.userService.readByEmail(email);
        if (containsRoles(user)) {
            this.staffService.updateStaffStatus(user.getId(), choose(isLocked));
            this.userService.updateIsLocked(email, isLocked);
        }
    }

    private boolean containsRoles(User user) {
        Collection<Role> roles = user.getRoles();
        return roles.stream()
                .map(Role::getRole)
                .anyMatch(r -> ROLE_ADMIN.equals(r) || ROLE_MANAGER.equals(r));
    }

}
