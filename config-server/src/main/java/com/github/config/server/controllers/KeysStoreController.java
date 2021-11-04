package com.github.config.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.config.server.entity.Instance;
import com.github.config.server.entity.Properties;
import com.github.config.server.exceptions.FailedDependencyException;
import com.github.config.server.service.InstanceService;
import com.github.config.server.service.PropertiesService;
import com.github.jwt.tokens.models.KeysInfo;
import com.github.jwt.tokens.models.KeysStore;
import com.github.jwt.tokens.utils.JwtKeyGenerator;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class KeysStoreController {

    private static final String KEYS_STORE = "keys.store";

    private final ObjectMapper mapper;

    private final JwtKeyGenerator jwtKeyGenerator;

    private final InstanceService instanceService;

    private final PropertiesService propertiesService;

    public KeysStoreController(ObjectMapper mapper, JwtKeyGenerator jwtKeyGenerator,
                               InstanceService instanceService, PropertiesService propertiesService) {
        this.mapper = mapper;
        this.jwtKeyGenerator = jwtKeyGenerator;
        this.instanceService = instanceService;
        this.propertiesService = propertiesService;
    }

    @PostMapping(path = "/{profile}")
    public void generateKeys(@PathVariable(name = "profile") String profile,
                             @RequestBody List<KeysInfo> payload) {
        newKeysStore(profile, payload);
    }

    @PutMapping(path = "/{profile}")
    public void regenerateKeys(@PathVariable String profile,
                               @RequestBody List<KeysInfo> payload) {
        this.propertiesService.removeAll();
        newKeysStore(profile, payload);
    }

    public void newKeysStore(String profile, List<KeysInfo> keys) {
        List<Instance> instances = this.instanceService.findAllInstances();
        Map<String, KeysStore> stores = keys.stream()
                .collect(Collectors.toMap(KeysInfo::getRole,
                        v -> this.jwtKeyGenerator.toKeysStore(v, SignatureAlgorithm.ES512))
                );
        this.propertiesService.createAll(
                instances.stream()
                        .map(instance -> new Properties(instance.getName(), profile, KEYS_STORE, toJson(stores)))
                        .collect(Collectors.toList())
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
