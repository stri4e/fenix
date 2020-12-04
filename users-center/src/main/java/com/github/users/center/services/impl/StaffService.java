package com.github.users.center.services.impl;

import com.github.users.center.dto.StaffDto;
import com.github.users.center.entity.EntityStatus;
import com.github.users.center.services.IStaffService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StaffService implements IStaffService {

    @Override
    public void createStaff(UUID managerId, StaffDto payload) {
    }

    @Override
    public void updateStaffStatus(UUID managerId, EntityStatus status) {

    }

}
