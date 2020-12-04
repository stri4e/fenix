package com.github.managers.services;

import com.github.managers.entity.EntityStatus;
import com.github.managers.entity.Staff;

import java.util.List;
import java.util.UUID;

public interface IStaffsService {

    Staff create(Staff staff);

    List<Staff> findAllByStatus(EntityStatus status);

    Staff findByManagerId(UUID managerId);

    void update(Long id, String firstName, String lastName, String email, String phone, String avatar);

    void update(UUID managerId, EntityStatus status);

    void remove(Long id);

}
