package com.github.users.center.services;

import com.github.users.center.dto.StaffDto;
import com.github.users.center.entity.EntityStatus;
import com.github.users.center.services.impl.StaffService;
import org.hibernate.event.internal.EntityState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(
        name = "managers",
        fallback = StaffService.class
)
public interface IStaffService {

    @PostMapping(
            path = "/v1/edit/{managerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void createStaff(@PathVariable(name = "managerId") UUID managerId,
                     @RequestBody StaffDto payload);

    @PutMapping(
            path = "/v1/edit/{managerId}/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void updateStaffStatus(@PathVariable(name = "managerId") UUID managerId,
                     @PathVariable(name = "status")EntityStatus status);

}
