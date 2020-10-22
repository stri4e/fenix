package com.github.users.center.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

public interface INotificationController {

    @GetMapping(
            path = "/push/ending/{userId}"
    )
    String endingForPush(@PathVariable(name = "userId") Long userId);

    @GetMapping(
            path = "/listening/ending"
    )
    String endingForListening(@RequestAttribute Long userId);


}
