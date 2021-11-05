package com.github.config.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.config.server.entity.Instance;
import com.github.config.server.entity.KeysSettings;
import com.github.config.server.entity.Properties;
import com.github.config.server.entity.Role;
import com.github.config.server.exceptions.FailedDependencyException;
import com.github.jwt.tokens.models.KeysInfo;
import com.github.jwt.tokens.models.KeysStore;
import com.github.jwt.tokens.utils.JwtKeyGenerator;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KeysStoreService {

    private static final String KEYS_STORE = "keys.store";

    private final ObjectMapper mapper;

    private final JwtKeyGenerator jwtKeyGenerator;

    private final InstanceService instanceService;

    private final PropertiesService propertiesService;

    public KeysStoreService(ObjectMapper mapper,
                            JwtKeyGenerator jwtKeyGenerator,
                            InstanceService instanceService,
                            PropertiesService propertiesService) {
        this.mapper = mapper;
        this.jwtKeyGenerator = jwtKeyGenerator;
        this.instanceService = instanceService;
        this.propertiesService = propertiesService;
    }

    public void newKeysStore(String profile) {
        List<Instance> instances = this.instanceService.findAllInstances();
        Map<String, KeysStore> stores = instances.stream()
                .flatMap(instance -> instance.getRoles().stream().distinct())
                .map(Role::getKeysSettings)
                .map(this::fromKeysSetting)
                .distinct()
                .collect(Collectors.toMap(KeysInfo::getRole,
                        v -> this.jwtKeyGenerator.toKeysStore(v, SignatureAlgorithm.HS512))
                );
        this.propertiesService.createAll(
                instances.stream()
                        .map(instance -> new Properties(instance.getName(), profile, KEYS_STORE, toJson(stores)))
                        .collect(Collectors.toList())
        );
    }

    public KeysInfo fromKeysSetting(KeysSettings key) {
        return new KeysInfo(
                key.getRole().getName(),
                key.getPriority(),
                key.getAccessTokenExpireTime(),
                key.getRefreshTokenExpireTime()
        );
    }

    public String toJson(Object data) {
        try {
            return this.mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new FailedDependencyException();
        }
    }

}
