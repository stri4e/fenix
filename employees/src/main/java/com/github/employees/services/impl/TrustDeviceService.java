package com.github.employees.services.impl;

import com.github.employees.entities.TrustDevice;
import com.github.employees.repository.TrustDeviceRepo;
import com.github.employees.services.ITrustDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrustDeviceService implements ITrustDeviceService {

    private final TrustDeviceRepo trustDeviceRepo;

    @Override
    public Mono<TrustDevice> create(TrustDevice data) {
        return this.trustDeviceRepo.save(data);
    }

    @Override
    public Flux<TrustDevice> readByEmployeeId(UUID employeeId) {
        return this.trustDeviceRepo.findByEmployeeId(employeeId);
    }

    @Override
    public Mono<TrustDevice> readById(String id) {
        return this.trustDeviceRepo.findById(id);
    }

    @Override
    public Mono<Void> update(TrustDevice data) {
        return this.trustDeviceRepo.save(data).then();
    }
}
