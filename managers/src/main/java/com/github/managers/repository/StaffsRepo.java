package com.github.managers.repository;

import com.github.managers.entity.EntityStatus;
import com.github.managers.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffsRepo extends JpaRepository<Staff, Long> {

    Optional<Staff> findByManagerId(UUID managerId);

    List<Staff> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Staff s set s.firstName=:firstName," +
            " s.lastName=:lastName, s.email=:email, s.phone=:phone, s.avatar=:avatar where s.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "firstName") String firstName,
            @Param(value = "lastName") String lastName,
            @Param(value = "email") String email,
            @Param(value = "phone") String phone,
            @Param(value = "avatar") String avatar
    );

    @Modifying
    @Query(value = "update Staff s set s.status=:status where s.managerId=:managerId")
    void update(
            @Param(value = "managerId") UUID managerId,
            @Param(value = "status") EntityStatus status
    );

    @Modifying
    @Query(value = "update Staff s set s.status=:status where s.id=:id")
    void remove(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
