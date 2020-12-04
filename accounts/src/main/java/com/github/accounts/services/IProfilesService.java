package com.github.accounts.services;

import com.github.accounts.entity.Profile;
import com.github.accounts.entity.Sex;

public interface IProfilesService {

    Profile create(Profile profile);

    Profile readById(Long id);

    void update(Long id, String firstName, String lastName, String patronymic, String dateOfBirth, Sex sex);

    void remove(Long id);

}
