package com.github.config.server.service;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Properties;
import com.github.config.server.entity.PropsType;

import java.util.List;

public interface IPropertiesService {

    void create(Properties p);

    void createAll(List<Properties> properties);

    void update(Properties p);

    void updateStatus(Long id, EntityStatus status);

    Properties readById(Long id);

    List<Properties> readByParams(String profile, PropsType propsType, EntityStatus status);

    void removeAll();

}
