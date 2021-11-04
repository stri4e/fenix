package com.github.config.server.service.impl;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Properties;
import com.github.config.server.entity.PropsType;
import com.github.config.server.exceptions.NotFound;
import com.github.config.server.repository.PropertiesRepo;
import com.github.config.server.service.IPropertiesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PropertiesService implements IPropertiesService {

    private final PropertiesRepo propertiesRepo;

    public PropertiesService(PropertiesRepo propertiesRepo) {
        this.propertiesRepo = propertiesRepo;
    }

    @Override
    public void create(Properties p) {
        this.propertiesRepo.save(p);
    }

    @Override
    public void createAll(List<Properties> properties) {
        this.propertiesRepo.saveAll(properties);
    }

    @Override
    public void update(Properties p) {
        this.propertiesRepo.save(p);
    }

    @Override
    public void updateStatus(Long id, EntityStatus status) {
        this.propertiesRepo.update(id, status);
    }

    @Override
    public Properties readById(Long id) {
        return this.propertiesRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<Properties> readByParams(String profile, PropsType propsType, EntityStatus status) {
        return this.propertiesRepo.findByProfileAndPropsTypeAndStatus(profile, propsType, status);
    }

    @Override
    public void removeAll() {
        this.propertiesRepo.deleteAll();
    }
}
