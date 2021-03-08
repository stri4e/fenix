package com.github.config.server.controllers;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Keystore;
import com.github.config.server.entity.Properties;
import com.github.config.server.entity.PropsType;
import com.github.config.server.service.IKeystoreService;
import com.github.config.server.service.IPropertiesService;
import com.github.config.server.service.JwtKeystoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/v1/generate/keystore")
public class JwtKeystoreController {

    private final JwtKeystoreService jwtKeystoreService;

    private final IPropertiesService propertiesService;

    private final IKeystoreService keystoreService;

    public JwtKeystoreController(JwtKeystoreService jwtKeystoreService,
                                 IPropertiesService propertiesService,
                                 IKeystoreService keystoreService) {
        this.jwtKeystoreService = jwtKeystoreService;
        this.propertiesService = propertiesService;
        this.keystoreService = keystoreService;
    }

    @PostMapping(path = "/applications/{profile}")
    public void generateKeystore(@PathVariable String profile, @RequestBody List<String> services) {
        generateNewKeystore(profile, services);
    }

    @PostMapping(path = "/application/{profile}/{service}")
    public void applicationKeystore(@PathVariable String profile, @PathVariable String service) {
        Keystore keystore = this.keystoreService.readActive();
        this.propertiesService.createAll(
                List.of(
                        new Properties(
                                service,
                                profile,
                                "keys.role",
                                keystore.getKeysRole(),
                                PropsType.keystore
                        ),
                        new Properties(
                                service,
                                profile,
                                "keys.store",
                                keystore.getKeystore(),
                                PropsType.keystore
                        )
                )
        );
    }

    @PutMapping(path = "/applications/{profile}")
    public void updateKeystoreStatus(@PathVariable String profile, @RequestBody List<String> services) {
        Keystore keystore = this.keystoreService.readActive();
        this.keystoreService.update(keystore.getId(), EntityStatus.off);
        List<Properties> oldProps = this.propertiesService.readByParams(profile, PropsType.keystore, EntityStatus.on);
        oldProps.forEach(p -> this.propertiesService.updateStatus(p.getId(), EntityStatus.off));
        generateNewKeystore(profile, services);
    }

    private void generateNewKeystore(String profile, List<String> services) {
        Map<String, String> roleAndId = this.jwtKeystoreService.mapRolesAndId();
        Map<String, String> idAndKey = this.jwtKeystoreService.mapIdAndKeys(roleAndId);
        var jsonRoleAndId = this.jwtKeystoreService.toJsonRoles(roleAndId);
        var jsonIdAndKey = this.jwtKeystoreService.toJsonKeys(idAndKey);
        this.keystoreService.create(new Keystore(profile, jsonRoleAndId, jsonIdAndKey));
        List<Properties> properties = services.stream()
                .flatMap(service -> Stream.of(
                        new Properties(
                                service,
                                profile,
                                "keys.role",
                                jsonRoleAndId,
                                PropsType.keystore
                        ),
                        new Properties(
                                service,
                                profile,
                                "keys.store",
                                jsonIdAndKey,
                                PropsType.keystore
                        )
                )).collect(Collectors.toList());
        this.propertiesService.createAll(properties);
    }

}
