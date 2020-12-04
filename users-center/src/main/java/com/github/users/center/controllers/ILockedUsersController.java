package com.github.users.center.controllers;

import com.github.users.center.dto.LockedDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ILockedUsersController {

    @PutMapping(path = "/edit")
    void lockedUser(@RequestBody LockedDto payload);

}
