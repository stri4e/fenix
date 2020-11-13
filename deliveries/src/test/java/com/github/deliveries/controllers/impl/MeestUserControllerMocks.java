package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.MeestUserDto;
import com.github.deliveries.entity.MeestUser;

public class MeestUserControllerMocks {

    public static MeestUserDto request() {
        return new MeestUserDto(
                "mest_user",
                "meest_password"
        );
    }

    public static MeestUserDto requestForUpdate() {
        return new MeestUserDto(
                "mest_user_supper",
                "meest_password_supper"
        );
    }

    public static MeestUser meestUserForSave() {
        return new MeestUser(
                "mest_user",
                "meest_password"
        );
    }

}
