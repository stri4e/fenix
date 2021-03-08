package com.github.config.server.controllers;

import com.github.config.server.dto.PropertiesDto;
import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Properties;
import com.github.config.server.entity.PropsType;
import com.github.config.server.service.IPropertiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/v1/properties")
public class PropertiesController {

    private final IPropertiesService propertiesService;

    public PropertiesController(IPropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @GetMapping(path = "/{profile}")
    public List<PropertiesDto> findAllProps(@PathVariable(name = "profile") String profile) {
        return this.propertiesService.readByParams(profile, PropsType.def, EntityStatus.on).stream()
                .map(p -> new PropertiesDto(
                        p.getId(),
                        p.getApplication(),
                        p.getKey(),
                        p.getValue()
                )).collect(Collectors.toList());
    }

    @PostMapping(path = "/{profile}")
    public void save(@PathVariable(name = "profile") String profile,
                     @RequestBody PropertiesDto payload) {
        this.propertiesService.create(
                new Properties(
                        payload.getApplication(),
                        profile,
                        payload.getKey(),
                        payload.getValue()
                )
        );
    }

    @PutMapping
    public void update(@RequestBody PropertiesDto payload) {
        Properties props = this.propertiesService.readById(payload.getId());
        props.setApplication(payload.getApplication());
        props.setKey(payload.getKey());
        props.setValue(payload.getValue());
        this.propertiesService.update(props);
    }

    @PutMapping(path = "/change/{id}/{status}")
    public void update(@PathVariable(value = "id") Long id,
                       @PathVariable(value = "status") EntityStatus status) {
        this.propertiesService.updateStatus(id, status);
    }

}
