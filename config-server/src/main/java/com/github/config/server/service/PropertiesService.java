package com.github.config.server.service;

import com.github.config.server.entity.Instance;
import com.github.config.server.entity.Properties;
import com.github.config.server.models.RolePermission;
import com.github.config.server.repository.InstanceRepo;
import com.github.config.server.repository.PropertiesRepo;
import com.github.config.server.repository.RoleRepo;
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

    private static final String KEYS_STORE = "keys.store";

    private static final String ROLES_STORE = "roles.store";

    private final RoleRepo roleRepo;

    private final JsonUtils jsonUtils;

    private final InstanceRepo instanceRepo;

    private final PropertiesRepo propertiesRepo;

    public PropertiesService(RoleRepo roleRepo,
                             JsonUtils jsonUtils,
                             InstanceRepo instanceRepo, PropertiesRepo propertiesRepo) {
        this.roleRepo = roleRepo;
        this.jsonUtils = jsonUtils;
        this.instanceRepo = instanceRepo;
        this.propertiesRepo = propertiesRepo;
    }

    @Transactional
    public void createNewKeysStore(Map<String, Map<String, KeysStore>> groupByInstances, String profile) {
        this.propertiesRepo.deleteAllByKey(KEYS_STORE);
        this.propertiesRepo.saveAll(
                groupByInstances.keySet().stream()
                        .map(instanceName -> new Properties(
                                instanceName,
                                profile,
                                KEYS_STORE,
                                this.jsonUtils.toJson(groupByInstances.get(instanceName)))
                        ).collect(toList())
        );
    }

    public void propertiesPermission(String profile) {
        List<RolePermission> roles = this.roleRepo.findAll().stream()
                .map(role -> new RolePermission(role.getId(), role.getName(), role.getPermission()))
                .collect(toList());
        List<Instance> instances = this.instanceRepo.findAll();
        this.propertiesRepo.saveAll(instances.stream()
                .map(instance -> new Properties(
                        instance.getName(),
                        profile,
                        ROLES_STORE,
                        this.jsonUtils.toJson(roles)
                )
        ).collect(toList()));
    }

    public void removeAllByKey(String key) {
        this.propertiesRepo.deleteAllByKey(key);
    }
}
