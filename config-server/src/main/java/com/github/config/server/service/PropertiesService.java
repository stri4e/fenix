package com.github.config.server.service;

import com.github.config.server.entity.Properties;
import com.github.config.server.repository.PropertiesRepo;
import com.github.config.server.utils.JsonUtils;
import com.github.jwt.tokens.models.KeysStore;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class PropertiesService {

    private final JsonUtils jsonUtils;

    private final PropertiesRepo propertiesRepo;

    public PropertiesService(JsonUtils jsonUtils, PropertiesRepo propertiesRepo) {
        this.jsonUtils = jsonUtils;
        this.propertiesRepo = propertiesRepo;
    }

    public void createAll(List<Properties> properties) {
        this.propertiesRepo.saveAll(properties);
    }

    public void createNewKeysStore(Map<String, Map<String, KeysStore>> groupByInstances, String profile, String keysStore) {
        this.propertiesRepo.saveAll(
                groupByInstances.keySet().stream()
                        .map(instanceName -> new Properties(
                                instanceName,
                                profile,
                                keysStore,
                                this.jsonUtils.toJson(groupByInstances.get(instanceName)))
                        ).collect(toList())
        );
    }

    public void removeAllByKey(String key) {
        this.propertiesRepo.deleteAllByKey(key);
    }
}
