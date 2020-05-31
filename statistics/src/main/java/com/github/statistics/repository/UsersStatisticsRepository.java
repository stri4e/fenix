package com.github.statistics.repository;

import com.github.statistics.entity.UsersStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersStatisticsRepository extends JpaRepository<UsersStatistics, Long> {
}
