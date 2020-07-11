package com.github.statistics.repository;

import com.github.statistics.entity.UsersStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UsersStatisticsRepository extends JpaRepository<UsersStatistics, Long> {

    Optional<UsersStatistics> findByUserId(Long userId);

    @Modifying
    @Query(value = "update UsersStatistics set lastLoginDate =: lastLoginDate," +
            " count =: count where id =: userId")
    void update(
            @Param(value = "userId") Long userId,
            @Param(value = "lastLoginDate") Date lastLoginDate,
            @Param(value = "count") Integer count
    );

}
