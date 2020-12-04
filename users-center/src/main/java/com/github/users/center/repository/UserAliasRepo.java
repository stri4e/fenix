package com.github.users.center.repository;

import com.github.users.center.entity.UserAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAliasRepo extends JpaRepository<UserAlias, Long> {

    @Query(value = "select n.alias from UserAlias n where n.user.id=:userId")
    String findAlias(@Param(value = "userId") UUID userId);

}
