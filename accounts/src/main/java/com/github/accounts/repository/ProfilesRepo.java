package com.github.accounts.repository;

import com.github.accounts.entity.EntityStatus;
import com.github.accounts.entity.Profile;
import com.github.accounts.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepo extends JpaRepository<Profile, Long> {

    @Modifying
    @Query(value = "update Profile p set p.firstName=:firstName, p.lastName=:lastName, p.patronymic=:patronymic, p.dateOfBirth=:dateOfBirth, p.sex=:sex where p.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "firstName") String firstName,
            @Param(value = "lastName") String lastName,
            @Param(value = "patronymic") String patronymic,
            @Param(value = "dateOfBirth") String dateOfBirth,
            @Param(value = "sex") Sex sex
    );

    @Modifying
    @Query(value = "update Profile p set p.status=:status where p.id=:id")
    void delete(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
