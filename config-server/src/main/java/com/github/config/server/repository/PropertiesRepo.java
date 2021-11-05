package com.github.config.server.repository;

import com.github.config.server.entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepo extends JpaRepository<Properties, Long> {

    void deleteAllByKey(String key);

}
