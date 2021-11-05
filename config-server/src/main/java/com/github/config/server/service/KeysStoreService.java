package com.github.config.server.service;

import com.github.config.server.entity.Instance;
import com.github.config.server.entity.KeysSettings;
import com.github.config.server.entity.Role;
import com.github.jwt.tokens.models.KeysInfo;
import com.github.jwt.tokens.models.KeysStore;
import com.github.jwt.tokens.utils.JwtKeyGenerator;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class KeysStoreService {

    private final JwtKeyGenerator jwtKeyGenerator;

    private final InstanceService instanceService;

    private final PropertiesService propertiesService;

    public KeysStoreService(JwtKeyGenerator jwtKeyGenerator,
                            InstanceService instanceService,
                            PropertiesService propertiesService) {
        this.jwtKeyGenerator = jwtKeyGenerator;
        this.instanceService = instanceService;
        this.propertiesService = propertiesService;
    }

    public void generateNewKeysStore(String profile) {
        List<Instance> instances = this.instanceService.findAllInstances();
        Map<String, KeysStore> stores = instances.stream()
                .flatMap(instance -> instance.getRoles().stream().distinct())
                .map(Role::getKeysSettings)
                .map(this::fromKeysSetting)
                .distinct()
                .collect(Collectors.toMap(
                                KeysInfo::getRole,
                                v -> this.jwtKeyGenerator.toKeysStore(v, SignatureAlgorithm.HS512)
                        )
                );
        Map<String, Map<String, KeysStore>> groupByInstances = instances.stream().collect(
                groupingBy(Instance::getName,
                        mapping(Instance::getRoles,
                                flatMapping(
                                        Collection::stream,
                                        toMap(Role::getName, role -> stores.get(role.getName())))
                        )
                )
        );
        this.propertiesService.createNewKeysStore(groupByInstances, profile);
    }

    public KeysInfo fromKeysSetting(KeysSettings key) {
        return new KeysInfo(
                key.getRole().getName(),
                key.getPriority(),
                key.getAccessTokenExpireTime(),
                key.getRefreshTokenExpireTime()
        );
    }

}
