package com.github.accounts.controllers.impl;

import com.github.accounts.dto.ProfileDto;
import com.github.accounts.entity.Profile;
import com.github.accounts.entity.Sex;

public class ProfilesControllerMocks {

    public static Profile profileForSave() {
        return new Profile(
                null,
                "Vasia",
                "Kolia",
                "Pupkin",
                "14.22.22",
                Sex.man
        );
    }

    public static ProfileDto response() {
        return new ProfileDto(
                1L,
                "Vasia",
                "Kolia",
                "Pupkin",
                "14.22.22",
                Sex.man
        );
    }

    public static ProfileDto request() {
        return new ProfileDto(
                null,
                "Vasia",
                "Kolia",
                "Pupkin",
                "14.22.22",
                Sex.man
        );
    }

    public static ProfileDto requestForUpdate() {
        return new ProfileDto(
                null,
                "Vasia",
                "Kolia",
                "Pupkin",
                "14.11.22",
                Sex.man
        );
    }

}
