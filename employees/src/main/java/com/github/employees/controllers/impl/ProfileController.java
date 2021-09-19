package com.github.employees.controllers.impl;

import com.github.employees.controllers.IProfileController;
import com.github.employees.payload.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/profiles")
public class ProfileController implements IProfileController {
    @Override
    public Mono<ProfileDto> save(String accountId, ProfileDto payload) {
        return null;
    }

    @Override
    public Mono<Void> update(String accountId, ProfileDto payload) {
        return null;
    }
}
