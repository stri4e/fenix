package com.github.accounts.services.impl;

import com.github.accounts.entity.EntityStatus;
import com.github.accounts.entity.Profile;
import com.github.accounts.entity.Sex;
import com.github.accounts.exceptions.NotFound;
import com.github.accounts.repository.ProfilesRepo;
import com.github.accounts.services.IProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfilesService implements IProfilesService {

    private final ProfilesRepo profilesRepo;

    @Override
    public Profile create(Profile profile) {
        return this.profilesRepo.save(profile);
    }

    @Override
    public Profile readById(Long id) {
        return this.profilesRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String firstName, String lastName, String patronymic, String dateOfBirth, Sex sex) {
        this.profilesRepo.update(id, firstName, lastName, patronymic, dateOfBirth, sex);
    }

    @Override
    public void remove(Long id) {
        this.profilesRepo.delete(id, EntityStatus.off);
    }
}
