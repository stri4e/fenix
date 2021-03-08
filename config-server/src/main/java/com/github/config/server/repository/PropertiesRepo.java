package com.github.config.server.repository;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.Properties;
import com.github.config.server.entity.PropsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepo extends JpaRepository<Properties, Long> {

    List<Properties> findByProfileAndPropsTypeAndStatus(String profile, PropsType propsType, EntityStatus status);

    @Modifying
    @Query(value = "update Properties p set p.status=:status where p.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
