package com.github.employees.controllers.impl;

import com.github.employees.controllers.ITrustDeviceController;
import com.github.employees.entities.EntityStatus;
import com.github.employees.payload.TrustDeviceDto;
import com.github.employees.services.ITrustDeviceService;
import com.github.employees.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.github.employees.utils.TransferObj.toTrustDevice;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/trusted/devices")
public class TrustDeviceController implements ITrustDeviceController {

    private final ITrustDeviceService trustDeviceService;

    @Override
    public Mono<TrustDeviceDto> save(UUID employeeId, TrustDeviceDto payload) {
        return this.trustDeviceService.create(toTrustDevice(employeeId, payload))
                .map(TransferObj::fromTrustDevice);
    }

    @Override
    public Flux<TrustDeviceDto> readAll(UUID employeeId) {
        return this.trustDeviceService.readByEmployeeId(employeeId)
                .map(TransferObj::fromTrustDevice);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.trustDeviceService.readById(id)
                .flatMap(device -> this.trustDeviceService.update(device.status(EntityStatus.off)));
    }


}
