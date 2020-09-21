package com.github.users.center.repository;

import com.github.users.center.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmailOrLogin(String email, String login);

    boolean existsByEmailOrLogin(String email, String login);

    @Modifying
    @Query(value = "update User u set pass =:pass where id =:id")
    void updatePass(
            @Param(value = "pass") String pass,
            @Param(value = "id") Long id);

    @Modifying
    @Query(value = "update User u set isEnable =:isEnable where id =:id")
    void updateIsEnable(
            @Param(value = "isEnable") boolean isEnable,
            @Param(value = "id") Long id);

    @Modifying
    @Query(value = "update User u set isLocked =:isLocked where email =:email")
    void updateIsLocked(
            @Param(value = "isLocked") boolean isLocked,
            @Param(value = "email") String id);

}
