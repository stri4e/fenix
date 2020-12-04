package com.github.managers.controllers.impl;

import com.github.managers.controllers.IStaffController;
import com.github.managers.dto.StaffDto;
import com.github.managers.entity.EntityStatus;
import com.github.managers.services.IStaffsService;
import com.github.managers.utils.Logging;
import com.github.managers.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.managers.utils.TransferObj.fromStaff;
import static com.github.managers.utils.TransferObj.toStaff;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class StaffController implements IStaffController {

    private final IStaffsService staffsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public StaffDto findStaff(UUID managerId) {
        return fromStaff(this.staffsService.findByManagerId(managerId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<StaffDto> findAllStaff() {
        return this.staffsService.findAllByStatus(EntityStatus.on).stream()
                .map(TransferObj::fromStaff)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public StaffDto save(UUID managerId, @Valid StaffDto payload) {
        return fromStaff(this.staffsService.create(toStaff(payload, managerId)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(StaffDto payload) {
        this.staffsService.update(
                payload.getId(),
                payload.getFirstName(),
                payload.getLastName(),
                payload.getEmail(),
                payload.getPhone(),
                payload.getAvatar()
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(UUID managerId, EntityStatus status) {
        this.staffsService.update(managerId, status);
    }

}
