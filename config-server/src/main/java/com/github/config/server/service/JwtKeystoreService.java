package com.github.config.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.KeysRoles;
import com.github.config.server.exceptions.FailedDependencyException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtKeystoreService {

    private final ObjectMapper objectMapper;

    private final IKeysRolesService keysRolesService;

    public JwtKeystoreService(ObjectMapper objectMapper, IKeysRolesService keysRolesService) {
        this.objectMapper = objectMapper;
        this.keysRolesService = keysRolesService;
    }

    public Map<String, String> mapRolesAndId() {
        return this.keysRolesService.readAll(EntityStatus.on).stream()
                .map(KeysRoles::getRole)
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> UUID.randomUUID().toString())
                );
    }

    public Map<String, String> mapIdAndKeys(Map<String, String> roleAndId) {
        return roleAndId.values().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded())
                ));
    }

    public String toJsonRoles(Map<String, String> roleAndId) {
        try {
            return this.objectMapper.writeValueAsString(roleAndId);
        } catch (JsonProcessingException e) {
            throw new FailedDependencyException();
        }
    }

    public String toJsonKeys(Map<String, String> idAndKey) {
        try {
            return this.objectMapper.writeValueAsString(idAndKey);
        } catch (JsonProcessingException e) {
            throw new FailedDependencyException();
        }
    }

}
