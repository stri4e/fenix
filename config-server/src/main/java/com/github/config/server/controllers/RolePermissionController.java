package com.github.config.server.controllers;

import com.github.config.server.service.PropertiesService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/role/permission")
public class RolePermissionController {

    private final PropertiesService propertiesService;

    public RolePermissionController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @PostMapping(path = "{profile}")
    public void rolePermission(@PathVariable String profile) {
        this.propertiesService.propertiesPermission(profile);
    }

}
