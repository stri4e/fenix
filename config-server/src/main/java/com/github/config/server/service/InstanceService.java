package com.github.config.server.service;

import com.github.config.server.entity.Instance;
import com.github.config.server.repository.InstanceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceService {

    private final InstanceRepo instanceRepo;

    public InstanceService(InstanceRepo instanceRepo) {
        this.instanceRepo = instanceRepo;
    }

    public List<Instance> findAllInstances() {
        return this.instanceRepo.findAll();
    }

}
