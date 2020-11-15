package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.ILockedUsersController;
import com.github.users.center.dto.LockedDto;
import com.github.users.center.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/locked")
public class LockedUsersController implements ILockedUsersController {

    private final IUserService userService;
    
    @Override
    public void lockedUser(LockedDto payload) {
        this.userService.updateIsLocked(payload.getEmail(), payload.isLocked());
    }

}
