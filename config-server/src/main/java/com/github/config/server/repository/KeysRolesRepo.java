package com.github.config.server.repository;

import com.github.config.server.entity.EntityStatus;
import com.github.config.server.entity.KeysRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeysRolesRepo extends JpaRepository<KeysRoles, Long> {

    List<KeysRoles> findAllByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update KeysRoles kr set kr.status=:status where kr.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
