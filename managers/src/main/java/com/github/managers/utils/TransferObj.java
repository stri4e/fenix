package com.github.managers.utils;

import com.github.managers.dto.StaffDto;
import com.github.managers.entity.Staff;

import java.util.UUID;

public class TransferObj {

    public static Staff toStaff(StaffDto data, UUID managerId) {
        return new Staff(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                data.getPhone(),
                data.getAvatar(),
                managerId
        );
    }

    public static StaffDto fromStaff(Staff data) {
        return new StaffDto(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                data.getPhone(),
                data.getAvatar()
        );
    }

}
