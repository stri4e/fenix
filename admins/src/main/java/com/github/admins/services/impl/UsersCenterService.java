package com.github.admins.services.impl;

import com.github.admins.dto.UserRegDto;
import com.github.admins.services.IUsersCenterService;
import org.springframework.stereotype.Service;

@Service
public class UsersCenterService implements IUsersCenterService {

    @Override
    public void createAdmins(UserRegDto payload) {

    }

    @Override
    public void createManager(UserRegDto payload) {

    }

    @Override
    public void updateManagersIsLocked(String email, Boolean isLocked) {

    }

    @Override
    public void updateAdminsIsLocked(String email, Boolean isLocked) {

    }
}
