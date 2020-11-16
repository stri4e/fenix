package com.github.deliveries.repository;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.MeestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeestUserRepo extends JpaRepository<MeestUser, Long> {

    Optional<MeestUser> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update MeestUser mu set mu.login=:login, mu.pass=:pass where mu.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "login") String login,
            @Param(value = "pass") String pass
    );

    @Modifying
    @Query(value = "update MeestUser mu set mu.status=:status where mu.id=:id")
    void delete(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
