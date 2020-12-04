package com.github.accounts.controllers.impl;

import com.github.accounts.controllers.IProfilesController;
import com.github.accounts.dto.ProfileDto;
import com.github.accounts.services.IProfilesService;
import com.github.accounts.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.accounts.utils.TransferObj.fromProfile;
import static com.github.accounts.utils.TransferObj.toProfile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/profiles")
public class ProfilesController implements IProfilesController {

    private final IProfilesService profilesService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ProfileDto findContact(Long profileId) {
        return fromProfile(this.profilesService.readById(profileId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ProfileDto save(ProfileDto payload) {
        return fromProfile(this.profilesService.create(toProfile(payload)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(ProfileDto payload) {
        this.profilesService.update(
                payload.getId(),
                payload.getFirstName(),
                payload.getLastName(),
                payload.getPatronymic(),
                payload.getDateOfBirth(),
                payload.getSex()
        );
    }

}
