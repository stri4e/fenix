package com.github.managers.services.impl;

import com.github.managers.entity.EntityStatus;
import com.github.managers.entity.Staff;
import com.github.managers.exceptions.NotFound;
import com.github.managers.repository.StaffsRepo;
import com.github.managers.services.IStaffsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffsService implements IStaffsService {

    private final StaffsRepo staffsRepo;

    @Override
    public Staff create(Staff staff) {
        return this.staffsRepo.save(staff);
    }

    @Override
    public List<Staff> findAllByStatus(EntityStatus status) {
        return this.staffsRepo.findByStatus(status);
    }

    @Override
    public Staff findByManagerId(UUID managerId) {
        return this.staffsRepo.findByManagerId(managerId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String firstName, String lastName, String email, String phone, String avatar) {
        this.staffsRepo.update(id, firstName, lastName, email, phone, avatar);
    }

    @Override
    public void update(UUID managerId, EntityStatus status) {
        this.staffsRepo.update(managerId, status);
    }

    @Override
    public void remove(Long id) {
        this.staffsRepo.remove(id, EntityStatus.off);
    }
}
