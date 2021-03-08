package com.github.config.server.controllers;

import com.github.config.server.dto.KeysRolesDto;
import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.KeysRoles;
import com.github.config.server.service.IKeysRolesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/v1/keys/roles")
public class KeysRolesController {

    private final IKeysRolesService keysRolesService;

    public KeysRolesController(IKeysRolesService keysRolesService) {
        this.keysRolesService = keysRolesService;
    }

    @GetMapping(path = "/{status}")
    public List<KeysRolesDto> findAll(@PathVariable EntityStatus status) {
        return this.keysRolesService.readAll(status).stream()
                .map(kr -> new KeysRolesDto(kr.getId(), kr.getRole()))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/{role}")
    public void save(@PathVariable(name = "role") String role) {
        this.keysRolesService.create(new KeysRoles(role));
    }

    @PutMapping(path = "/{id}/{status}")
    public void update(@PathVariable Long id, @PathVariable EntityStatus status) {
        this.keysRolesService.updateStatus(id, status);
    }

}
