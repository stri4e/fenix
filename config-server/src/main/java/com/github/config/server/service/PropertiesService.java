package com.github.config.server.service;

import com.github.config.server.entity.Properties;
import com.github.config.server.repository.PropertiesRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PropertiesService {

    private final PropertiesRepo propertiesRepo;

    public PropertiesService(PropertiesRepo propertiesRepo) {
        this.propertiesRepo = propertiesRepo;
    }

    public void createAll(List<Properties> properties) {
        this.propertiesRepo.saveAll(properties);
    }

    public void removeAll() {
        this.propertiesRepo.deleteAll();
    }
}
